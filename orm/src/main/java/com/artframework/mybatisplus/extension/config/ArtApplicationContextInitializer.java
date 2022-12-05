package com.artframework.mybatisplus.extension.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.bind.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 加载组件的配置文件
 *
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/21
 **/
@Slf4j
public class ArtApplicationContextInitializer implements ApplicationContextInitializer {
    private static final String FILE_NAME = "orm.xml";

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TableConfiguration tableConfiguration = getOuterConfig();
        if (null != tableConfiguration) {
            GlobalTableConfiguration.Init(tableConfiguration);
            return;
        }

        tableConfiguration = getInnerConfig();
        if (null != tableConfiguration) {
            GlobalTableConfiguration.Init(tableConfiguration);
            return;
        }

        GlobalTableConfiguration.Init(TableConfiguration.DEFAULT());
    }

    /**
     * 获取jar资源包内部配置
     *
     * @return
     */
    private TableConfiguration getInnerConfig() {
        InputStream stream = null;
        try {
            stream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            TableConfiguration tableConfiguration = getConfiguration(stream);
            if (null != stream) {
                stream.close();
            }

            return tableConfiguration;
        } catch (JAXBException | IOException e) {
            log.error("load inner orm.xml config file failed.", e);
        } finally {
            if (null != stream) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * 获取jar资源包外部配置
     *
     * @return
     */
    private TableConfiguration getOuterConfig() {
        FileInputStream fis =null;
        try {
            String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            String[] pathSplit = path.split("/");
            String jarName = pathSplit[pathSplit.length - 1];
            String jarPath = path.replace(jarName, "");
            String pathName = jarPath + FILE_NAME;

             fis = new FileInputStream(pathName);
            TableConfiguration tableConfiguration = getConfiguration(fis);


            return tableConfiguration;
        } catch (JAXBException | IOException e) {
            log.error("load outer orm.xml config file failed.", e);
        }finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private TableConfiguration getConfiguration(InputStream stream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TableConfiguration.class, TableConfiguration.Table.class, TableConfiguration.Table.TableColumn.class);    // 获取上下文对象
        Unmarshaller unmarshaller = context.createUnmarshaller(); // 根据上下文获取marshaller对象
        return  (TableConfiguration) unmarshaller.unmarshal(stream);
    }
}
