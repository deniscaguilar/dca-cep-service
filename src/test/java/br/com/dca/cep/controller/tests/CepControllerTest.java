package br.com.dca.cep.controller.tests;

import br.com.dca.cep.CepServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CepServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles
public class CepControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void validarBuscaEnderecoCepValido() throws Exception {
        String cep = "09725160";
        this.mvc.perform(get(String.format("/cep/%s", cep)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(setContentType("charset=utf-8"))
                .andExpect(jsonPath("localidade", equalTo("São Bernardo do Campo")))
                .andExpect(jsonPath("uf", equalTo("SP")));
    }

}
