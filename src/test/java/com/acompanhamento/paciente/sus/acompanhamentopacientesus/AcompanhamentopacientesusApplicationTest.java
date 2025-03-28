package com.acompanhamento.paciente.sus.acompanhamentopacientesus;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class AcompanhamentopacientesusApplicationTest {

  @Test
  void mainMethodStartsApplication() {
    assertDoesNotThrow(() -> AcompanhamentopacientesusApplication.main(new String[] {}));
  }
}
