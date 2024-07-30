package com.psy.demo.utils.auth;

/**
 * @author xy-peng
 */
public interface Signer {

    SignatureResult sign(byte[] message);

    class SignatureResult {

        protected final String sign;
        protected final String certificateSerialNumber;

        public String getSign() {
            return sign;
        }

        public String getCertificateSerialNumber() {
            return certificateSerialNumber;
        }

        public SignatureResult(String sign, String serialNumber) {
            this.sign = sign;
            this.certificateSerialNumber = serialNumber;
        }
    }

}
