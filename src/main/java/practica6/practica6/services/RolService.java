package practica6.practica6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import practica6.practica6.models.Rol;

@Service
public class RolService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rol> obtenerRoles() {
        String sql = "SELECT id, nombre FROM roles";
        return jdbcTemplate.query(sql, rolRowMapper());
    }

    private RowMapper<Rol> rolRowMapper() {
        return (rs, rowNum) -> {
            Rol rol = new Rol();
            rol.setId(rs.getLong("id"));
            rol.setNombre(rs.getString("nombre"));
            return rol;
        };
    }

    public void agregarRol(String roleName) {
        String sql = "INSERT INTO roles (nombre) VALUES (?)";  
        jdbcTemplate.update(sql, roleName);  
    }
    public void actualizarRol(Long id, String nuevoNombre) {
        String sql = "UPDATE roles SET nombre = ? WHERE id = ?";
        jdbcTemplate.update(sql, nuevoNombre, id);
    }
    
    public void eliminarRol(Long id) {
        String sql = "DELETE FROM roles WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    public Rol obtenerRolPorId(Long id) {
        String sql = "SELECT id, nombre FROM roles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rolRowMapper(), id);
    }

    
}
