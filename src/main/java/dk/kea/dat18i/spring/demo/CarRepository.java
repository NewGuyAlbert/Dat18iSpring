package dk.kea.dat18i.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate jdbc;


    public List<Car> findAllCars(){
       SqlRowSet rs = jdbc.queryForRowSet("select * from cars ");
       List<Car> carList= new ArrayList<>();
       while(rs.next()){
           Car car = new Car();
           car.setId(rs.getInt("id"));
           car.setBrand(rs.getString("brand"));
           car.setColor(rs.getString("color"));
           car.setReg(rs.getString("reg"));
           car.setMaxSpeed(rs.getDouble("max_speed"));

           carList.add(car);
       }
       return carList;
    }

    public void addCars(Car car)
    {


        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO cars VALUES(null , ?,?,?,?)", new String[]{"id"});
                ps.setString(1, car.getReg());
                ps.setString(2, car.getBrand());
                ps.setString(3, car.getColor());
                ps.setDouble(4, car.getMaxSpeed());
                return ps;
            }
        };

        jdbc.update(psc);

    }

    public void deleteCar(int index)
    {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM cars WHERE id =?");
                ps.setInt(1,index);
                return ps;
            }
        };

        jdbc.update(psc);


    }

    public void editCar(Car car)
    {
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE cars SET reg=?,brand=?,color=?,max_speed=? WHERE id =?");
                ps.setString(1,car.getReg());
                ps.setString(2,car.getBrand());
                ps.setString(3,car.getColor());
                ps.setDouble(4,car.getMaxSpeed());
                ps.setInt(5,car.getId());
                return ps;
            }
        };

        jdbc.update(psc);


    }

}
