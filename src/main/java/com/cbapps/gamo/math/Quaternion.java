package com.cbapps.gamo.math;

public record Quaternion(
        double x,
        double y,
        double z,
        double w) {

    /**
     * Constructs a new Quaternion from the given axis and angle.
     * @param axis the rotation axis.
     * @param angleDegrees the rotation angle in degrees.
     * @return A new Quaternion.
     */
    public static Quaternion of(Vector3 axis, double angleDegrees) {
        var angle = Math.toRadians(angleDegrees);
        var s = Math.sin(angle / 2);
        var w = Math.cos(angle / 2);

        var x = axis.x() * s;
        var y = axis.y() * s;
        var z = axis.z() * s;

        return new Quaternion(x, y, z, w);
    }

    public static Quaternion identity() {
        return new Quaternion(0, 0, 0, 1);
    }

    public Quaternion divide(double scale) {
        var nw = w / scale;
        var nx = x / scale;
        var ny = y / scale;
        var nz = z / scale;

        return new Quaternion(nx, ny, nz, nw);
    }

    public double dot(Quaternion q) {
        return x * q.x + y * q.y + z * q.z + w * q.w;
    }

    public boolean equals(Quaternion other) {
        return x == other.x && y == other.y && z == other.z && w == other.w;
    }

    public Quaternion lerp(Quaternion other, double progress) {
        if (!equals(other)) {
            double d = dot(other);
            double qx, qy, qz, qw;

            if (d < 0f) {
                qx = -other.x;
                qy = -other.y;
                qz = -other.z;
                qw = -other.w;
                d = -d;
            } else {
                qx = other.x;
                qy = other.y;
                qz = other.z;
                qw = other.w;
            }

            double f0, f1;

            if ((1 - d) > 0.1f) {
                double angle = Math.acos(d);
                double s = Math.sin(angle);
                double tAngle = progress * angle;
                f0 = Math.sin(angle - tAngle) / s;
                f1 = Math.sin(tAngle) / s;
            } else {
                f0 = 1 - progress;
                f1 = progress;
            }

            var nx = f0 * x + f1 * qx;
            var ny = f0 * y + f1 * qy;
            var nz = f0 * z + f1 * qz;
            var nw = f0 * w + f1 * qw;

            return new Quaternion(nx, ny, nz, nw);
        }

        return this;
    }

    public Vector3 multiply(Vector3 point) {
        var tx = x * 2;
        var ty = y * 2;
        var tz = z * 2;
        var xx = x * tx;
        var yy = y * ty;
        var zz = z * tz;
        var xy = x * ty;
        var xz = x * tz;
        var yz = y * tz;
        var wx = w * tx;
        var wy = w * ty;
        var wz = w * tz;

        var nx = (1F - (yy + zz)) * point.x() + (xy - wz) * point.y() + (xz + wy) * point.z();
        var ny = (xy + wz) * point.x() + (1F - (xx + zz)) * point.y() + (yz - wx) * point.z();
        var nz = (xz - wy) * point.x() + (yz + wx) * point.y() + (1F - (xx + yy)) * point.z();

        return new Vector3(nx, ny, nz);
    }


    public Quaternion multiply(Quaternion other) {
        var nw = w * other.w - x * other.x - y * other.y - z * other.z;
        var nx = w * other.x + x * other.w + y * other.z - z * other.y;
        var ny = w * other.y + y * other.w + z * other.x - x * other.z;
        var nz = w * other.z + z * other.w + x * other.y - y * other.x;

        return new Quaternion(nx, ny, nz, nw);
    }

    public Quaternion normalize() {
        return divide(Math.sqrt(dot(this)));
    }

    public Quaternion scale(double scale) {
        var nw = w * scale;
        var nx = x * scale;
        var ny = y * scale;
        var nz = z * scale;

        return new Quaternion(nx, ny, nz, nw);
    }

    /**
     * Converts this Quaternion into a matrix, returning it as a float array.
     */
    public double[] toMatrix() {
        double[] matrixs = new double[16];
        toMatrix(matrixs);
        return matrixs;
    }

    /**
     * Converts this Quaternion into a matrix, placing the values into the given array.
     * @param matrixs 16-length float array.
     */
    public void toMatrix(double[] matrixs) {
        matrixs[0] = (1.0 - (2.0 * ((y * y) + (z * z))));
        matrixs[1] = (2.0 * ((x * y) - (z * w)));
        matrixs[2] = (2.0 * ((x * z) + (y * w)));
        matrixs[3] = 0.0;

        matrixs[4] = (2.0 * ((x * y) + (z * w)));
        matrixs[5] = (1.0 - (2.0 * ((x * x) + (z * z))));
        matrixs[6] = (2.0 * ((y * z) - (x * w)));
        matrixs[7] = 0.0;

        matrixs[8] = (2.0 * ((x * z) - (y * w)));
        matrixs[9] = (2.0 * ((y * z) + (x * w)));
        matrixs[10] = (1.0 - (2.0 * ((x * x) + (y * y))));
        matrixs[11] = 0.0;

        matrixs[12] = 0.0;
        matrixs[13] = 0.0;
        matrixs[14] = 0.0;
        matrixs[15] = 1.0;
    }

    @Override
    public String toString() {
        return String.format("Quaternion{x=%.1f, y=%.1f, z=%.1f, w=%.1f}", x, y, z, w);
    }
}
