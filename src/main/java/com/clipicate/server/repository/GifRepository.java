package com.clipicate.server.repository;

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.clipicate.server.classes.Gif;

public interface GifRepository extends JpaRepository<Gif, Long> {
}