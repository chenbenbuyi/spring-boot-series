//package cnblogs.chenbenbuy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//public class SpringSecurityConfiguration {
//
//    @Bean
//    public UserDetailsService myUserDetailsService() {
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//
//        String[][] usersGroupsAndRoles = {
//                {"salaboy", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"ryandawsonuk", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"erdemedeiros", "password", "ROLE_ACTIVITI_USER", "GROUP_activitiTeam"},
//                {"other", "password", "ROLE_ACTIVITI_USER", "GROUP_otherTeam"},
//                {"system", "password", "ROLE_ACTIVITI_USER"},
//                {"admin", "password", "ROLE_ACTIVITI_ADMIN"},
//        };
//
//        for (String[] user : usersGroupsAndRoles) {
//            List<String> authoritiesStrings = Arrays.asList(Arrays.copyOfRange(user, 2, user.length));
//            inMemoryUserDetailsManager.createUser(new User(user[0], passwordEncoder().encode(user[1]),
//                    authoritiesStrings.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())));
//        }
//        return inMemoryUserDetailsManager;
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}