/**
 * 
 */
package com.pivotaltracker.api.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.pivotaltracker.api.domain.model.PivotalStory;

/**
 * @author anders
 * 
 */
public class PivotalService {

    private final Logger LOGGER = LoggerFactory.getLogger(PivotalService.class);
    public static final String PIVOTAL_API_URL = "http://www.pivotaltracker.com/services/v3/projects/";
    public static final String USER_TOKEN_URL = "https://www.pivotaltracker.com/services/v3/tokens/active";

    private String token = "";
    private String projectId;
    private Credentials credentials;

    public PivotalService(String token) {
        this.token = token;
    }

    public PivotalService(String token, String projectId) {
        this.token = token;
        this.projectId = projectId;
    }

    public PivotalService(PivotalCredentials credentials) {
        this.credentials = new UsernamePasswordCredentials(credentials.getUserName(), credentials.getPassword());
        setUserToken();
    }

    public PivotalService(PivotalCredentials credentials, String projectId) {
        this.credentials = new UsernamePasswordCredentials(credentials.getUserName(), credentials.getPassword());
        this.projectId = projectId;
        setUserToken();
    }

    private void setUserToken() {
        // Use basicAuth to make the request to the server
        HttpResponse response = requestBasicAuthentication();
        HttpEntity entity = response.getEntity();

        if ((response.getStatusLine().getStatusCode() == 200) && (entity.getContentLength() > 1)) {
            XPath xpath = XPathFactory.newInstance().newXPath();
            String expression = "/token/guid";
            InputStream responseContent;
            try {
                responseContent = response.getEntity().getContent();
                InputSource inputSource = new InputSource(responseContent);
                this.token = xpath.evaluate(expression, inputSource);
                LOGGER.debug("Token fetched: {}", token);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }

    private HttpResponse requestBasicAuthentication() {
        DefaultHttpClient httpclient = null;
        HttpResponse response = null;
        try {
            httpclient = new DefaultHttpClient();
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(null, 443), credentials);
            HttpGet httpget = new HttpGet(USER_TOKEN_URL);
            LOGGER.debug("Executing request: {}",  httpget.getRequestLine());

            response = httpclient.execute(httpget);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                httpclient.getConnectionManager().shutdown();
            }
        }
        return response;
    }

    public String addStory(PivotalStory story) {
        String url = PIVOTAL_API_URL + projectId + "/stories?token=" + token;

        String storyXml = "<story><story_type>" + story.getStoryType() + "</story_type><name>" + story.getName()
                + "</name><requested_by>" + story.getRequestor() + "</requested_by>";
        if (!StringUtils.isBlank(story.getDescription()))
            storyXml += "<description>" + story.getDescription() + "</description>";
        storyXml += "</story>";

        return addStoryOrTask(url, storyXml);
    }

    public String addTask(String storyId, String description) {
        String url = PIVOTAL_API_URL + projectId + "/stories/" + storyId + "/tasks?token=" + token;

        String task = "<task><description>" + description + "</description></task>";

        return addStoryOrTask(url, task);
    }

    public String AddNote() {
        //
        return "";
    }

    private String addStoryOrTask(String url, String data) {
        return null;
        // HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
        //
        // // Set the ContentType property.
        // request.ContentType = "application/xml";
        //
        // // Set the Method property to 'POST' to post data to the URI.
        // request.Method = "POST";
        // request.Proxy = WebProxy.GetDefaultProxy();
        // byte[] byteArray = Encoding.UTF8.GetBytes(data);
        // request.ContentLength = byteArray.Length;
        //
        // try
        // {
        // Stream dataStream = request.GetRequestStream();
        // dataStream.Write(byteArray, 0, byteArray.Length);
        //
        // dataStream.Close();
        // }
        // catch (Exception ex)
        // {
        // return ex.Message;
        // }
        //
        // Stream objStream;
        //
        // try
        // {
        // objStream = request.GetResponse().GetResponseStream();
        // }
        // catch (Exception ex)
        // {
        // return ex.Message;
        // }
        //
        // StreamReader objReader = new StreamReader(objStream);
        //
        // String sLine = "";
        // int i = 0;
        // StringBuilder sb = new StringBuilder();
        //
        // while (sLine != null)
        // {
        // i++;
        // sLine = objReader.ReadLine();
        // if (sLine != null)
        // {
        // sb.Append(sLine); //Console.WriteLine("{0}:{1}", i, sLine);
        // }
        // }
        // //Console.ReadLine();
        //        
        // String xml = sb.ToString();
        // XmlDocument xmlDoc = new XmlDocument();
        // xmlDoc.LoadXml(xml);
        //
        // XmlNode urlNode = xmlDoc.SelectSingleNode("/story/url");
        // return "Pivotal story created: " + urlNode.FirstChild.InnerText ?? "(couldn't load url)";
    }

    public List<PivotalStory> getStories(String project) {
        return Collections.emptyList();
//
//        String url = PIVOTAL_API_URL + projectId + "/stories?token=" + token;
//
//        URL uri = new URL(url);
//
//        // Create a new HttpWebRequest object.
//        HttpWebRequest request = (HttpWebRequest) WebRequest.Create(uri);
//
//        request.Proxy = WebProxy.GetDefaultProxy();
//
//        Stream objStream;
//        objStream = request.GetResponse().GetResponseStream();
//
//        StreamReader objReader = new StreamReader(objStream);
//
//        String sLine = "";
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//
//        while (sLine != null) {
//            i++;
//            sLine = objReader.ReadLine();
//            if (sLine != null) {
//                Console.WriteLine("{0}:{1}", i, sLine);
//                sb.Append(sLine);
//            }
//
//        }
//        Console.ReadLine();
//
//        String xml = sb.ToString();
//
//        XmlDocument xmlDoc = new XmlDocument();
//
//        xmlDoc.LoadXml(xml);
//
//        xmlDoc.Save(StoryXmlDirectory);
//
//        StreamReader str = new StreamReader(StoryXmlDirectory);
//        XmlSerializer xSerializer = new XmlSerializer(typeof(PivotalStory));
//
//        PivotalStory myStories = (PivotalStory) xSerializer.Deserialize(str);
//
//        // foreach (storiesStory story in myStories.story)
//        // {
//        // Debug.Write(String.Format("Name: {0} {1}", story.name, Environment.NewLine));
//        // Debug.Write(String.Format("Description: {0} {1}", story.description, Environment.NewLine));
//        // Debug.Write(Environment.NewLine);
//
//        // }
//
//        str.Close();
//
//        return null;

    }

}
