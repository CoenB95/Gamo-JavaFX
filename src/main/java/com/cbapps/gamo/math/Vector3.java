package com.cbapps.gamo.math;

public record Vector3(
        double x,
        double y,
        double z) {

    public static Vector3 identity() {
        return new Vector3(0, 0, 0);
    }

    public static Vector3 unit(double x, double y, double z) {
        return new Vector3(x, 0, 0).normalised();
    }

    public static Vector3 unitX() {
        return new Vector3(1, 0, 0);
    }

    public static Vector3 unitY() {
        return new Vector3(0, 1, 0);
    }

    public static Vector3 unitZ() {
        return new Vector3(0, 0, 1);
    }

    public static Vector3 x(double x) {
        return new Vector3(x, 0, 0);
    }

    public static Vector3 y(double y) {
        return new Vector3(0, y, 0);
    }

    public static Vector3 z(double z) {
        return new Vector3(0, 0, z);
    }

    public Vector3 add(Vector3 other) {
        var nx = x + other.x;
        var ny = y + other.y;
        var nz = z + other.z;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 cross(Vector3 other) {
        var nx = y * other.z - z * other.y;
        var ny = z * other.x - x * other.z;
        var nz = x * other.y - y * other.x;

        return new Vector3(nx, ny, nz);
    }

    public double distance(Vector3 other)
    {
        var diffX = x - other.x;
        var diffY = y - other.y;
        var diffZ = z - other.z;

        return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
    }

    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3 lerp(Vector3 other, double progress) {
        var nx = x + (other.x - x) * progress;
        var ny = y + (other.y - y) * progress;
        var nz = z + (other.z - z) * progress;

        return new Vector3(nx, ny, nz);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 multiply(double value) {
        var nx = x * value;
        var ny = y * value;
        var nz = z * value;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 multiply(Vector3 other) {
        var nx = x * other.x;
        var ny = y * other.y;
        var nz = z * other.z;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 normalised() {
        var mag = magnitude();
        var nx = x / mag;
        var ny = y / mag;
        var nz = z / mag;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 scale(double scale) {
        var nx = x * scale;
        var ny = y * scale;
        var nz = z * scale;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 scale(Vector3 scale) {
        var nx = x * scale.x;
        var ny = y * scale.y;
        var nz = z * scale.z;

        return new Vector3(nx, ny, nz);
    }

    public Vector3 subtract(Vector3 other) {
        var nx = x - other.x;
        var ny = y - other.y;
        var nz = z - other.z;

        return new Vector3(nx, ny, nz);
    }

    @Override
    public String toString() {
        return String.format("x=%.2f, y=%.2f, z=%.2f", x, y, z);
    }
}
