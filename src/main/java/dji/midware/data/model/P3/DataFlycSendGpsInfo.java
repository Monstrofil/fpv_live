package dji.midware.data.model.P3;

import android.support.annotation.Keep;
import dji.fieldAnnotation.EXClassNullAway;
import dji.midware.data.config.P3.CmdIdFlyc;
import dji.midware.data.config.P3.CmdSet;
import dji.midware.data.config.P3.DataConfig;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.DataBase;
import dji.midware.data.packages.P3.SendPack;
import dji.midware.interfaces.DJIDataCallBack;
import dji.midware.interfaces.DJIDataSyncListener;
import dji.midware.util.BytesUtil;

@Keep
@EXClassNullAway
public class DataFlycSendGpsInfo extends DataBase implements DJIDataSyncListener {
    private static DataFlycSendGpsInfo instance = null;
    private double latitude;
    private double longitude;
    private short mAltitude = 0;
    private short mHeading = 0;

    public static synchronized DataFlycSendGpsInfo getInstance() {
        DataFlycSendGpsInfo dataFlycSendGpsInfo;
        synchronized (DataFlycSendGpsInfo.class) {
            if (instance == null) {
                instance = new DataFlycSendGpsInfo();
            }
            dataFlycSendGpsInfo = instance;
        }
        return dataFlycSendGpsInfo;
    }

    public DataFlycSendGpsInfo setLatitude(double latitude2) {
        this.latitude = latitude2;
        return this;
    }

    public DataFlycSendGpsInfo setLongitude(double longitude2) {
        this.longitude = longitude2;
        return this;
    }

    public DataFlycSendGpsInfo setAltitude(short attitude) {
        this.mAltitude = attitude;
        return this;
    }

    public DataFlycSendGpsInfo setHeading(short heading) {
        this.mHeading = heading;
        return this;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public void start(DJIDataCallBack callBack) {
        SendPack pack = new SendPack();
        pack.senderType = DeviceType.APP.value();
        pack.receiverType = DeviceType.FLYC.value();
        pack.cmdType = DataConfig.CMDTYPE.REQUEST.value();
        pack.isNeedAck = DataConfig.NEEDACK.YES.value();
        pack.encryptType = DataConfig.EncryptType.NO.value();
        pack.cmdSet = CmdSet.FLYC.value();
        pack.cmdId = CmdIdFlyc.CmdIdType.SendGpsInfo.value();
        super.start(pack, callBack);
    }

    /* access modifiers changed from: protected */
    public void doPack() {
        this._sendData = new byte[20];
        System.arraycopy(BytesUtil.getBytes(this.latitude), 0, this._sendData, 0, 8);
        System.arraycopy(BytesUtil.getBytes(this.longitude), 0, this._sendData, 8, 8);
        System.arraycopy(BytesUtil.getBytes(this.mAltitude), 0, this._sendData, 16, 2);
        System.arraycopy(BytesUtil.getBytes(this.mHeading), 0, this._sendData, 18, 2);
    }
}
