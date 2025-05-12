package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DamageReportRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapper database-rækker til DamageReport-objekter
    private final RowMapper<DamageReport> rowMapper = new RowMapper<>() {
        @Override
        public DamageReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DamageReport(
                    rs.getInt("reportId"),
                    rs.getObject("date", LocalDate.class),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("inspection"),
                    rs.getBoolean("customerPresent"),
                    rs.getString("status"),
                    rs.getInt("carId"),
                    rs.getString("attachmentPath")
            );
        }
    };

    // Henter alle skadesrapporter
    public List<DamageReport> findAll() {
        String sql = "SELECT * FROM damage_reports";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Henter én skadesrapport baseret på ID
    public DamageReport findById(int id) {
        String sql = "SELECT * FROM damage_reports WHERE reportId = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Henter alle skadesrapporter for en bestemt bil
    public List<DamageReport> findByCarId(int carId) {
        String sql = "SELECT * FROM damage_reports WHERE carId = ?";
        return jdbcTemplate.query(sql, rowMapper, carId);
    }

    // Gemmer en ny skadesrapport
    public void save(DamageReport report) {
        String sql = "INSERT INTO damage_reports (reportId, date, description, price, inspection, customerPresent, status, carId, attachmentPath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                report.getReportId(),
                report.getDate(),
                report.getDescription(),
                report.getPrice(),
                report.getInspection(),
                report.isCustomerPresent(),
                report.getStatus(),
                report.getCarId(),
                report.getAttachmentPath()
        );
    }

    // Opdaterer en eksisterende skadesrapport
    public void update(DamageReport report) {
        String sql = "UPDATE damage_reports SET date = ?, description = ?, price = ?, inspection = ?, customerPresent = ?, status = ?, carId = ?, attachmentPath = ? WHERE reportId = ?";
        jdbcTemplate.update(sql,
                report.getDate(),
                report.getDescription(),
                report.getPrice(),
                report.getInspection(),
                report.isCustomerPresent(),
                report.getStatus(),
                report.getCarId(),
                report.getAttachmentPath(),
                report.getReportId()
        );
    }

    // Sletter en skadesrapport baseret på ID
    public void deleteById(int id) {
        String sql = "DELETE FROM damage_reports WHERE reportId = ?";
        jdbcTemplate.update(sql, id);
    }
}
