package xyz.grinner.happyproxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.grinner.happyproxy.manager.Fisher;

@SpringBootTest
class HappyProxyApplicationTests {

    @Autowired
    private Fisher fisher;

    @Test
    void lihe() {
        fisher.lihe();
    }

    @Test
    void crossin() {
        fisher.crossin();
    }
    @Test
    void freeip() {
        fisher.freeip();
    }

    @Test
    void bajiu() {
        fisher.bajiu();
    }

    @Test
    void xici() {
        fisher.xici();
    }

    @Test
    void kuaidaili() {
        fisher.kuaidaili();
    }

    @Test
    void liuliu() {
        fisher.liuliu();
    }
    @Test
    void goubanjia() {
        fisher.goubanjia();
    }

}
