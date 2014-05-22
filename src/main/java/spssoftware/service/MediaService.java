package spssoftware.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import spssoftware.dao.KeyDao;
import spssoftware.domain.Album;
import spssoftware.domain.Photo;

import java.util.HashMap;
import java.util.Map;

@Service
public class MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);
    public static final String ROOT_URL = "https://api.copy.com/rest/meta/copy/content/hicksfamilyhistory";
    public static final String SITE = "hicksfamilyhistory";

    private KeyDao keyDao;

    private static Map<String, Map<String, Album>> albums = new HashMap<>();

    @Autowired
    public MediaService(KeyDao keyDao) {
        this.keyDao = keyDao;
    }

    public Map<String, Map<String, Album>> getContent() {

        albums = new HashMap<>();

        if (albums.size() == 0) {

            JSONObject root = JSONObject.parseObject(get(ROOT_URL));

            JSONArray links = root.getJSONArray("links");
            if (links.size() > 0) {
                String copyUrl = links.getJSONObject(0).getString("url");
                String albumLinkUrl = copyUrl + "/" + SITE;
                String token = copyUrl.substring(copyUrl.lastIndexOf("/"));

                JSONArray children = root.getJSONArray("children");
                for (int i = 0; i < children.size(); i++) {

                    Map<String, Album> albumMap = new HashMap<>();

                    String separator = children.getJSONObject(i).getString("name");

                    String separatorFolderContentsResponse = get(ROOT_URL + "/" + separator);
                    if (separatorFolderContentsResponse != null) {
                        JSONObject separatorFolderContents = JSONObject.parseObject(separatorFolderContentsResponse);

                        JSONArray separatorFolderContentsList = separatorFolderContents.getJSONArray("children");
                        for (int j = 0; j < separatorFolderContentsList.size(); j++) {

                            JSONObject separatorFolderContentsListItem = separatorFolderContentsList.getJSONObject(j);

                            String albumName = separatorFolderContentsListItem.getString("name");

                            Album album = new Album();
                            if (albumName.contains("^")) {
                                String[] nameParts = albumName.split("^");
                                album.setName(nameParts[1]);
                                album.setDate(nameParts[0]);
                            } else {
                                album.setName(albumName);
                            }

                            String albumFolderResponse = get(ROOT_URL + "/" + separator + "/" + albumName);

                            if (albumFolderResponse != null) {
                                JSONObject albumFolder = JSONObject.parseObject(albumFolderResponse);

                                JSONArray albumFileList = albumFolder.getJSONArray("children");

                                for (int k = 0; k < albumFileList.size(); k++) {

                                    JSONObject albumFileListItem = albumFileList.getJSONObject(k);
                                    String filename = albumFileListItem.getString("name");

                                    Photo photo = new Photo();
                                    photo.setFilename(filename);
                                    photo.setUrl(albumLinkUrl + "/" + separator + "/" + albumName + "/" + filename);
                                    photo.setThumbnailUrl("https://copy.com/thumbs_public" + token + "/" + SITE + "/" + separator + "/" + albumName + "/" + filename + "?size={size}");
                                    if (filename.startsWith("cover")) {
                                        album.setCoverPhoto(photo);
                                    } else {
                                        if (!filename.startsWith("DSC")) {
                                            photo.setCaption(filename.substring(0, filename.indexOf(".")));
                                        }
                                        album.getPhotos().add(photo);
                                    }
                                }
                                albumMap.put(album.getName(), album);
                            }
                        }
                    }

                    albums.put(separator, albumMap);
                }
            }
        }

        return albums;
    }


    private String get(String url) {

        Client client = new Client();
        // baseline OAuth parameters for access to resource
        OAuthParameters params = new OAuthParameters().signatureMethod("HMAC-SHA1")
                .nonce()
                .timestamp()
                .version("1")
                .consumerKey(keyDao.getByKey("CONSUMER_KEY"))
                .token(keyDao.getByKey("TOKEN"));

        OAuthSecrets secrets = new OAuthSecrets()
                .consumerSecret(keyDao.getByKey("CONSUMER_SECRET"))
                .tokenSecret(keyDao.getByKey("TOKEN_SECRET"));

        OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);

        String encodedUrl = UriComponentsBuilder.fromHttpUrl(url).build(false).encode().toUriString();

        WebResource webResource = client.resource(encodedUrl);
        webResource.addFilter(filter);

        WebResource.Builder builder = webResource.header("Accept", MediaType.APPLICATION_JSON).header("X-Api-Version","1");

        ClientResponse response = builder.get(ClientResponse .class);
        if (HttpStatus.OK.value() == response.getStatus()) {
            return response.getEntity(String.class);
        } else {
            logger.error("Call to " + url + " returned a response code " + response.getStatus());
            return null;
        }
    }
}
