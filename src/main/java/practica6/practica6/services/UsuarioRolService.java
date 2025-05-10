package practica6.practica6.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import practica6.practica6.models.UsuarioRol;

@Service
public class UsuarioRolService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UsuarioRol> obtenerUsuariosRoles() {
        String sql = "SELECT usuario_id, rol_id FROM usuarios_roles";

        return jdbcTemplate.query(sql, usuarioRolRowMapper());
    }

    private RowMapper<UsuarioRol> usuarioRolRowMapper() {
        return (rs, rowNum) -> {
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setUsuarioId(rs.getLong("usuario_id"));
            usuarioRol.setRolId(rs.getLong("rol_id"));
            return usuarioRol;
        };
    }
    
}
