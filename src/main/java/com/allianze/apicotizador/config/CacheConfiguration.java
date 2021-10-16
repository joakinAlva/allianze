package com.allianze.apicotizador.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.allianze.apicotizador.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.allianze.apicotizador.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.allianze.apicotizador.domain.User.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.Authority.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.User.class.getName() + ".authorities");
            createCache(cm, com.allianze.apicotizador.domain.TCPERFIL.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCPERFIL.class.getName() + ".tMUSUARIOS");
            createCache(cm, com.allianze.apicotizador.domain.TMUSUARIO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCUNIDADNEGOCIO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCEJECUTIVO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCSUBGRUPO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCTIPOREGLA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCTIPO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCOCUPACION.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCDESCUENTOTIPORIESGO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCPRIMANETASDESC.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCFACTORSAMI.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCDESCUENTOSUMAASEGURADA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCPRIMATARIFA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCRECARGOPAGOFRACCIONADO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCOBERTURA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCONCEPTO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCHIPOTESIS.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCOEFICIENTE.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCFACTORDESCUENTO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCSUMAASEGURADA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCRANGOPRIMA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCTIPOPRODUCTO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCREFENCIA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCUOTARIESGO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCUOTAVALOR.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCUOTATARIFASDESC.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCUOTAPROPUESTA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCEDADRECARGO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCOVID.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCREGIONAL.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCCOVIDTARIFAS.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TCESTATUSCOTIZACION.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TMCOTIZACION.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TMCOTIZACIONINFO.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TMCOTIZACIONEXPPROPIA.class.getName());
            createCache(cm, com.allianze.apicotizador.domain.TMASEGURADO.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
