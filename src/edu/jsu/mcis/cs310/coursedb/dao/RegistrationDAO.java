package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {

    private final DAOFactory daoFactory;

    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean create(int studentid, int termid, int crn) {

        boolean result = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                // START
                String sql = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    result = true;
                }
                // END
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public boolean delete(int studentid, int termid, int crn) {

        boolean result = false;

        PreparedStatement ps = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                // START
                String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    result = true;
                }
                // END
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public boolean delete(int studentid, int termid) {

        boolean result = false;

        PreparedStatement ps = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {
                // START
                String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    result = true;
                }
                // END
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public String list(int studentid, int termid) {

        String result = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                // START
                String sql = "SELECT studentid, termid, crn FROM registration " +
                             "WHERE studentid = ? AND termid = ? ORDER BY crn";

                ps = conn.prepareStatement(sql);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                rs = ps.executeQuery();

                result = DAOUtility.getResultSetAsJson(rs);
                // END
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

}
