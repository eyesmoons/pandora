package com.pandora.admin.config;

import com.pandora.admin.PandoraApplication;
import com.pandora.common.config.PandoraConfig;
import com.pandora.common.constant.Constants.UploadSubDir;
import java.io.File;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = PandoraApplication.class)
@RunWith(SpringRunner.class)
public class PandoraConfigTest {

    @Autowired
    private PandoraConfig config;

    @Test
    public void testConfig() {
        String fileBaseDir = "/tmp/pandora/profile";

        Assertions.assertEquals("pandora", config.getName());
        Assertions.assertEquals("1.0.0", config.getVersion());
        Assertions.assertEquals("2022", config.getCopyrightYear());
        Assertions.assertEquals(true, config.isDemoEnabled());
        Assertions.assertEquals(fileBaseDir, PandoraConfig.getFileBaseDir());
        Assertions.assertEquals(false, PandoraConfig.isAddressEnabled());
        Assertions.assertEquals("math", PandoraConfig.getCaptchaType());
        Assertions.assertEquals("math", PandoraConfig.getCaptchaType());
        Assertions.assertEquals(fileBaseDir + "\\import",
            PandoraConfig.getFileBaseDir() + File.separator + UploadSubDir.IMPORT_PATH);
        Assertions.assertEquals(fileBaseDir + "\\avatar",
            PandoraConfig.getFileBaseDir() + File.separator + UploadSubDir.AVATAR_PATH);
        Assertions.assertEquals(fileBaseDir + "\\download",
            PandoraConfig.getFileBaseDir() + File.separator + UploadSubDir.DOWNLOAD_PATH);
        Assertions.assertEquals(fileBaseDir + "\\upload",
            PandoraConfig.getFileBaseDir() + File.separator + UploadSubDir.UPLOAD_PATH);
    }

}
