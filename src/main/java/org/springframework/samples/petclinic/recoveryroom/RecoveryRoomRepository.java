package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer> {

    List<RecoveryRoom> findAll();
    
    @Query("SELECT r FROM RecoveryRoomType r")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();

    @Query("SELECT r FROM RecoveryRoomType r WHERE r.name=:roomType")
    RecoveryRoomType findRecoveryRoomTypeByName(@Param("roomType") String roomType);

    @Query("SELECT r FROM RecoveryRoom r WHERE r.size>:tam")
    List<RecoveryRoom> findBySizeMoreThan(@Param("tam") double tam);
    
    Optional<RecoveryRoom> findById(int id);

    @SuppressWarnings("unchecked")
    RecoveryRoom save(RecoveryRoom p);
    
    //RecoveryRoomType getRecoveryRoomType(String name);
    //List<RecoveryRoom> findBySizeMoreThan(double size);
}
