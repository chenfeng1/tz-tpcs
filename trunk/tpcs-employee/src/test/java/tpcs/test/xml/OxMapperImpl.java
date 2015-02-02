package tpcs.test.xml;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hu Jing Ling on 2015/1/26.
 */
public class OxMapperImpl implements OxMapper {

    private Marshaller marshaller;
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    private Unmarshaller unmarshaller;
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void writeObjectToXml(Object object, String filename) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            marshaller.marshal(object, new StreamResult(fos));
        } catch (XmlMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public Object readObjectFromXml(String filename) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            return unmarshaller.unmarshal(new StreamSource(fis));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return null;
    }
}
