import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ilakk.develop.connected.cities.controller.ConnectedCitiesController;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class ConnetctedCitiesControllerTest {

	@Test
	void test() {
		ConnectedCitiesController controller = new ConnectedCitiesController();
		String actualResult = controller.isConnected("Boston", "Newark");
		assertEquals(actualResult, "yes");
	}

}
