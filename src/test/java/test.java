import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClientBuilder;
import org.jfrog.artifactory.client.model.File;
import org.jfrog.artifactory.client.model.Repository;
import org.jfrog.artifactory.client.model.User;
import org.jfrog.artifactory.client.model.builder.UserBuilder;
import org.jfrog.artifactory.client.model.repository.settings.impl.DebianRepositorySettingsImpl;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static java.lang.System.out;

//Makes sure tests runs in order
@FixMethodOrder(MethodSorters.JVM)
public class test {
    //make objects global
    Artifactory artifactory;
    User user;
    static Random random = new Random();
    private static int x = random.nextInt(900) + 100;
    private static String xString = Integer.toString(x);
    //to avoid class name - and make global
    private static String repoUrl = "http://jfrog.local:80/artifactory";
    private static String repoUsername = "admin";
    private static String repoPass = "1qazxsw2";
    private static String repoName = "idan"+xString;
    private static String filePath = "/Users/idan/IdeaProjects/junit9/src/test/java/fileToUpload.txt";
    private static String fileName = "/idan"+xString+".txt";
    private static String CreatingUserName = "idan"+xString;


    @Before
    public void connect() {
         artifactory = ArtifactoryClientBuilder.create()
                .setUrl(repoUrl)
                .setUsername(repoUsername)
                .setPassword(repoPass)
                .build();
    }

    @Test
    public void createRepo() {
        DebianRepositorySettingsImpl settings = new DebianRepositorySettingsImpl();
        settings.setDebianTrivialLayout(true);

        Repository repository = artifactory.repositories()
                .builders()
                .localRepositoryBuilder()
                .key(repoName)
                .description("new local repository")
                .repositorySettings(settings)
                .build();

        String result = artifactory.repositories().create(2, repository);
    }

    @Test
    public void testConnection() {
        Repository repo = artifactory.repository(repoName).get();
        out.println(repo);
    }

    @Test
    public void testUploadFile() {
        java.io.File file = new java.io.File(filePath);
        File result = artifactory.repository(repoName).upload(fileName, file).doUpload();

    }
    @Test
    public void testUserCreation() {
        UserBuilder userBuilder = artifactory.security().builders().userBuilder();
        user = userBuilder.name(CreatingUserName)
                .email(CreatingUserName+"@mail.com")
                .admin(true)
                .profileUpdatable(true)
                .password("q1q1q1q1q1q1")
                .build();

        artifactory.security().createOrUpdate(user);

    }
    @Test
    public void testDownloadfile() throws IOException {
        Artifactory localConnection = ArtifactoryClientBuilder.create()
                .setUrl(repoUrl)
                .setUsername(CreatingUserName)
                .setPassword("q1q1q1q1q1q1")
                .build();
        InputStream iStream = localConnection.repository(repoName)
                .download(fileName)
                .doDownload();
        out.println(localConnection.getUsername());
    }
}
