package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.hardware.lynx.LynxDcMotorController;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ControlHub {
    private final int[] BULK_DATA_CACHE = new int[4]; //All the data from the control hub
    private final int[] JUNK_TICKS = new int[4]; //Ticks from the last time the motor was spun (4 values because four ports)

    //Lynxmodule is the control/expansion hubs
    private final LynxModule hubModule;
    //Accesses the motor-controlling parts
    private final LynxDcMotorController motorController;

    public ControlHub(HardwareMap hardware, String hubName) {
        hubModule = hardware.get(LynxModule.class, hubName);
        motorController = hardware.get(LynxDcMotorController.class, hubName);

        hubModule.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        refreshBulkData();

        setJunkTicks();
    }

    public void setJUNK_TICKS(int motor) {
        JUNK_TICKS[motor] = BULK_DATA_CACHE[motor];
    }

    //Sets all motors' junk ticks to 0
    public void setJunkTicks() {
        refreshBulkData();
        for (int i = 0; i < 4; i++) {
            // Calls the overload that sets junkTicks based on bulkDataCache
            setJUNK_TICKS(i);
            }
    }

    /**
     * @param motor What motor's junk ticks you're setting
     * @param junkTick What you're setting the junk ticks to
     */
    public void setJunkTicks(int motor, Integer junkTick) {
        JUNK_TICKS[motor] = (junkTick != null) ? junkTick : BULK_DATA_CACHE[motor];
    }

    //Update the ticks
    public void refreshBulkData() {
        hubModule.clearBulkCache();
        for (int i = 0; i < 4; i++) {
            BULK_DATA_CACHE[i] = motorController.getMotorCurrentPosition(i);
        }
    }

    public int getEncoderTicks(int motor) {
        return BULK_DATA_CACHE[motor] - JUNK_TICKS[motor];
    }
}