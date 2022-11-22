// package com.example.projetointegrador.model;

// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;

// import java.util.HashSet;
// import java.util.Set;

// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// @ActiveProfiles("test")
// @SpringBootTest
// @AutoConfigureMockMvc
// public class UserUTest {

//     @Test
//     public void test(){
//         Long id = 1L;
//         String name = "name";
//         String email = "email";
//         UserType userType = new UserType();
//         Set<Product> products = new HashSet<>();
//         Set<Storage> storages = new HashSet<>();

//         UserU userU = new UserU();
//         userU.setId(id);
//         userU.setName(name);
//         userU.setEmail(email);
//         userU.setUserType(userType);
//         userU.setProducts(products);
//         userU.setStorages(storages);

//         assertThat(id).isEqualTo(userU.getId());
//         assertThat(name).isEqualTo(userU.getName());
//         assertThat(email).isEqualTo(userU.getEmail());
//         assertThat(userType).isEqualTo(userU.getUserType());
//         assertThat(products).isEqualTo(userU.getProducts());
//         assertThat(storages).isEqualTo(userU.getStorages());

//     }
// }
