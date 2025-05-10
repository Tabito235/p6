package practica6.practica6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import practica6.practica6.models.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Usuario> obtenerUsuarios() {
        String sql = "SELECT id, email, password FROM usuarios";
        return jdbcTemplate.query(sql, usuarioRowMapper()); 
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        String sql = "SELECT id, email, password FROM usuarios WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, usuarioRowMapper());
    }

    public void agregarUsuario(String email, String password, Long rolId) {
        String sqlUsuario = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sqlUsuario, email, password);

        Long usuarioId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        String sqlUsuarioRol = "INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (?, ?)";
        jdbcTemplate.update(sqlUsuarioRol, usuarioId, rolId);
    }

    public void actualizarUsuario(Long id, String email, String password, Long rolId) {
        String sql = "UPDATE usuarios SET email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, email, password, id);

        String deleteSql = "DELETE FROM usuarios_roles WHERE usuario_id = ?";
        jdbcTemplate.update(deleteSql, id);

        String insertSql = "INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (?, ?)";
        jdbcTemplate.update(insertSql, id, rolId);
    }

    public void eliminarUsuario(Long id) {
        String deleteRolesSql = "DELETE FROM usuarios_roles WHERE usuario_id = ?";
        jdbcTemplate.update(deleteRolesSql, id);

        String deleteUsuarioSql = "DELETE FROM usuarios WHERE id = ?";
        jdbcTemplate.update(deleteUsuarioSql, id);
    }

    private RowMapper<Usuario> usuarioRowMapper() {
        return (rs, rowNum) -> {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getLong("id"));
            usuario.setEmail(rs.getString("email"));
            usuario.setPassword(rs.getString("password"));
            return usuario;
        };
    }
}
