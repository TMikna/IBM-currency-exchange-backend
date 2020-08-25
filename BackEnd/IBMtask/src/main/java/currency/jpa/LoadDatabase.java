// DB testing  //


//package currency.jpa;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import currency.logging.LogRepository;
////import currency.logging.userAction;
//
//import java.util.Date;
//
//@Configuration
//class LoadDatabase {
//
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(Repository repository, LogRepository logRepo){
//        return args -> {
//            log.info("Preloading " + repository.save(new FxRate(Long.valueOf(111), "2020-02-02", "AAA", 1)));
//            log.info("Preloading " + repository.save(new FxRate(Long.valueOf(112), "2020-08-25", "CCC", 12.96)));
//            
//            log.info("Preloading log " + logRepo.save(new userAction(Long.valueOf(112), new Date(), "Something happened")));
//            log.info("Preloading log " + logRepo.save(new userAction(Long.valueOf(113), new Date(), "Something happened again")));
//
//            
//        };
//    }
//}