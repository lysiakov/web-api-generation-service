package ua.nure.lysiakov.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import ua.nure.lysiakov.endpoint.StudentEndpoint;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
    MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
    dispatcherServlet.setApplicationContext(context);
    dispatcherServlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(dispatcherServlet, "/ws/*");
  }

  @Bean(name = "students")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("StudentsPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace(StudentEndpoint.NAMESPACE_URI);
    wsdl11Definition.setSchema(schema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema studentSchema() {
    return new SimpleXsdSchema(new ClassPathResource("students.xsd"));
  }
}
