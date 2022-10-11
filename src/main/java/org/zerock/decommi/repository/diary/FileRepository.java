package org.zerock.decommi.repository.diary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

  @Query("select f from File f where f.dino.dino=:dino")
  List<File> findByDino(Long dino);

  @Modifying
  @Query("delete from File f where f.dino.dino=:dino")
  void deleteFileByDino(Long dino);

  @Transactional
  @Modifying
  @Query("delete from File f where f.uuid=:uuid")
  void deleteByUuid(String uuid);

  @Query("select t from Tag t where diary_dino=:dino ")
  List<File> getList(Long dino);
}
