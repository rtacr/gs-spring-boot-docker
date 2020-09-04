package hello;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SuruculerController {
    @GetMapping("/getDrivers")
    @ResponseBody
    public ResponseEntity<?> getInfos() {
        return getDriverList();
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    private ResponseEntity<Map<String, Object>> getDriverList() {

        Map<String, Object> list = new HashMap<>();
        jdbcTemplate.query("SELECT * FROM DRIVER", (rs) -> {
            list.put(rs.getString("TC_KIMLIK"),
                    new BusDriver(Long.parseLong(rs.getString("TC_KIMLIK")), rs.getString("Ad")));
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getDriver")
    @ResponseBody
    public ResponseEntity<?> getDriver(@RequestParam("id") String tc_kimlik) {
        Map<String, Object> list = new HashMap<>();

        String query = "SELECT * FROM DRIVER WHERE TC_KIMLIK=" + tc_kimlik;
        jdbcTemplate.query(query, (rs) -> {
            list.put(rs.getString("TC_KIMLIK"),
                    new BusDriver(Long.parseLong(rs.getString("TC_KIMLIK")), rs.getString("Ad")));
        });

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/employees")
    String replaceEmployee(@RequestBody String body) {
        System.out.println(body);
        //JSONObject obj = new JSONObject(body);
        BusDriver newDriver = new BusDriver((long) 0, "ad");
        String query = "INSERT INTO DRIVER values (" + newDriver.getTc_kimlik() + "," + newDriver.getAd() + ")";
        jdbcTemplate.query(query, (rs) -> {
        });
        return "Success";
        // return repository.findById(id)
        // .map(employee -> {
        // employee.setName(newEmployee.getName());
        // employee.setRole(newEmployee.getRole());
        // return repository.save(employee);
        // })
        // .orElseGet(() -> {
        // newEmployee.setId(id);
        // return repository.save(newEmployee);
        // });
    }
}
