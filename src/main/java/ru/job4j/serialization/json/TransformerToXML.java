package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class TransformerToXML {
    public static void main(String[] args) throws Exception {
        Transformer tr = new Transformer(
                true,
                10,
                "DobriiUBIVATOR",
                new Team("Autobot"),
                new String[] {"Earth", "Mars", "Cybertron"}
        );

        JAXBContext context = JAXBContext.newInstance(Transformer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(tr, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Transformer rsl = (Transformer) unmarshaller.unmarshal(reader);
            System.out.println(rsl);
        }
    }
}
