package com.pupilmed.fitnesse;

import fit.Fixture;
import com.pupilmed.pupilmedapi.PupilmedapiApplication;
public class SetUp extends Fixture{
    static PupilmedapiApplication aplikacja;
    public SetUp() {
        aplikacja = new PupilmedapiApplication();
        System.out.println("SetUp initialized!");
    }
}