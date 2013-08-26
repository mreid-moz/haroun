import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * A class for writing various types of integer values to an OutputStream
 * So far only the Little-endian variations are properly tested.
 * Code was inspired by 
 *   http://stackoverflow.com/questions/7830175/writing-unsigned-int-of-4-bytes-over-network
 */
public class BitTwiddling {
    // Shared by both -endians - write one byte.
    public static void writeUInt8(int uint8, OutputStream stream) throws IOException {
        stream.write(uint8 & 0xFF);
    }

    // Little-endian
    public static void writeUInt16LE(int uint16, OutputStream stream) throws IOException {
        writeUInt8(uint16, stream);
        writeUInt8(uint16 >> 8, stream);
    }

    // Big-endian
    public static void writeUInt16BE(int uint16, OutputStream stream) throws IOException {
        writeUInt8(uint16 >> 8, stream);
        writeUInt8(uint16, stream);
    }

    // Little-endian
    public static void writeUInt32LE(long uint32, OutputStream stream) throws IOException {
        writeUInt16LE((int)uint32 & 0x0000FFFF, stream);
        writeUInt16LE((int)(uint32 & 0xFFFF0000) >> 16, stream);
    }

    // Big-endian
    public static void writeUInt32BE(long uint32, OutputStream stream) throws IOException {
        writeUInt16BE((int)(uint32 & 0xFFFF0000) >> 16, stream);
        writeUInt16BE((int)uint32 & 0x0000FFFF, stream);
    }

    // Little-endian
    public static void writeUInt64LE(long uint64, OutputStream stream) throws IOException {
        writeUInt32LE(uint64 & 0x00000000FFFFFFFF, stream);
        writeUInt32LE(uint64 >> 32, stream);
    }

    // Big-endian
    public static void writeUInt64BE(long uint64, OutputStream stream) throws IOException {
        writeUInt32BE(uint64 >> 32, stream);
        writeUInt32BE(uint64 & 0x00000000FFFFFFFF, stream);
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        String path = args[1];
        String data = args[2];
        long timestamp = System.currentTimeMillis();
        System.out.println(String.format("Writing to '%s': path = '%s', data = '%s', timestamp = %d", filename, path, data, timestamp));
        test(filename, path, data, timestamp);
    }

    // Note: you can validate the data you wrote using 'unpack_log.py' here:
    // https://github.com/mreid-moz/telemetry-server/blob/node_server/node/unpack_log.py
    public static void test(String filename, String path, String data, 
            long timestamp) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filename)));
        for (int i = 0; i < 3; i++) {
            System.out.println("Writing line " + i);
            BitTwiddling.writeUInt32LE((long)path.getBytes().length, out);
            BitTwiddling.writeUInt32LE((long)data.getBytes().length, out);
            BitTwiddling.writeUInt64LE(timestamp, out);
            out.write(path.getBytes());
            out.write(data.getBytes());
        }
        out.flush();
        out.close();
    }
}
