package com.Mofit.MofitBackend.domain.stamina.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.stamina.dto.StaminaRequest;
import com.Mofit.MofitBackend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stamina extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int plank;

    private int chairSquat;

    private int pushUp;

    private int stepTest;

    private int forwardFold;

    private float balance;

    public Stamina(User user){
        this.user = user;
    }
    public void patchStamina(StaminaRequest staminaRequest){
        this.plank = staminaRequest.getPlank();
        this.chairSquat = staminaRequest.getChairSquat();
        this.pushUp = staminaRequest.getPushUp();
        this.stepTest = staminaRequest.getStepTest();
        this.forwardFold = staminaRequest.getForwardFold();
        this.balance = staminaRequest.getBalance();
    }
}
