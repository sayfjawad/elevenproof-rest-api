package nl.multicode.elevenproof.config;

import nl.multicode.elevenproof.service.ElevenproofBeforeBigBang;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public ElevenproofBeforeBigBang getElevenproofBeforeBigBang() {

        return new ElevenproofBeforeBigBang();
    }
}
