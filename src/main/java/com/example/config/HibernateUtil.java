package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtil {

    private static StandardServiceRegistry registery;
    private static SessionFactory sessionFactory;

    @Bean
    public static SessionFactory getSessionFactory() {

        System.out.println(">>>>>>>>>>HibernateUtil.getSessionFactory");
        try {
            if(sessionFactory == null) {

                //Create registry
                registery =  new StandardServiceRegistryBuilder().configure().build();

                // Create Metadata
                MetadataSources sources = new MetadataSources(registery);
                MetadataBuilder ver = sources.getMetadataBuilder();
                ver.build();
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFac
                // tory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            }
        } catch (Exception ex) {
            if(registery != null) {
                StandardServiceRegistryBuilder.destroy(registery);
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }

    public static void shutDown() {
        if(registery != null) {
            StandardServiceRegistryBuilder.destroy(registery);
        }
    }
}
