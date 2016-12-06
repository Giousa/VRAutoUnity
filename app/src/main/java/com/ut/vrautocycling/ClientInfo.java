package com.ut.vrautocycling;

/**
 * Description:
 * Author：Giousa
 * Date：2016/7/24
 */
public class ClientInfo {
    public ClientInfo() {
    }
    private String clientId;
    private String deviceId;
    private int resistance;
    private int teamId;

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientId='" + clientId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
