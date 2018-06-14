package com.svega.crypto.common

import com.svega.common.math.UInt8
import com.svega.common.math.and
import com.svega.common.math.toUInt8
import java.util.*

object CryptoOps {
    fun load_3(in_: Array<UInt8>, off: Int): Long {
        var result = (in_[0 + off] and 0xff.toUInt8()).toLong()
        result = result or ((in_[1 + off] and 0xff.toUInt8()).toLong() shl 8)
        result = result or ((in_[2 + off] and 0xff.toUInt8()).toLong() shl 16)
        return result
    }

    fun load_4(in_: Array<UInt8>, off: Int): Long{
        var result = (in_[0 + off] and 0xff.toUInt8()).toLong()
        result = result or ((in_[1 + off] and 0xff.toUInt8()).toLong() shl 8)
        result = result or ((in_[2 + off] and 0xff.toUInt8()).toLong() shl 16)
        result = result or ((in_[3 + off] and 0xff.toUInt8()).toLong() shl 24)
        return result
    }

    fun load_3(in_: ByteArray, off: Int): Long {
        var result = (in_[0 + off].toInt() and 0xff).toLong()
        result = result or ((in_[1 + off].toInt() and 0xff).toLong() shl 8)
        result = result or ((in_[2 + off].toInt() and 0xff).toLong() shl 16)
        return result
    }

    fun load_4(in_: ByteArray, off: Int): Long{
        var result = (in_[0 + off].toInt() and 0xff).toLong()
        result = result or ((in_[1 + off].toInt() and 0xff).toLong() shl 8)
        result = result or ((in_[2 + off].toInt() and 0xff).toLong() shl 16)
        result = result or ((in_[3 + off].toInt() and 0xff).toLong() shl 24)
        return result
    }

    fun sc_reduce32(s: Array<UInt8>):Array<UInt8> {
        val ret = Arrays.copyOf(s, 32)
        var s0 = 2097151L and load_3(ret, 0)
        var s1 = 2097151L and (load_4(ret, 2) shr 5)
        var s2 = 2097151L and (load_3(ret, 5) shr 2)
        var s3 = 2097151L and (load_4(ret, 7) shr 7)
        var s4 = 2097151L and (load_4(ret, 10) shr 4)
        var s5 = 2097151L and (load_3(ret, 13) shr 1)
        var s6 = 2097151L and (load_4(ret, 15) shr 6)
        var s7 = 2097151L and (load_3(ret, 18) shr 3)
        var s8 = 2097151L and load_3(ret, 21)
        var s9 = 2097151L and (load_4(ret, 23) shr 5)
        var s10 = 2097151L and (load_3(ret, 26) shr 2)
        var s11 = (load_4(ret, 28) shr 7)
        var s12 = 0L
        var carry0: Long
        var carry1: Long
        var carry2: Long
        var carry3: Long
        var carry4: Long
        var carry5: Long
        var carry6: Long
        var carry7: Long
        var carry8: Long
        var carry9: Long
        var carry10: Long
        var carry11: Long

        carry0 = (s0 + (1 shl 20)) shr 21
        s1 += carry0
        s0 -= carry0  shl  21
        carry2 = (s2 + (1 shl 20)) shr 21
        s3 += carry2
        s2 -= carry2  shl  21
        carry4 = (s4 + (1 shl 20)) shr 21
        s5 += carry4
        s4 -= carry4  shl  21
        carry6 = (s6 + (1 shl 20)) shr 21
        s7 += carry6
        s6 -= carry6  shl  21
        carry8 = (s8 + (1 shl 20)) shr 21
        s9 += carry8
        s8 -= carry8  shl  21
        carry10 = (s10 + (1 shl 20)) shr 21
        s11 += carry10
        s10 -= carry10  shl  21

        carry1 = (s1 + (1 shl 20)) shr 21
        s2 += carry1
        s1 -= carry1  shl  21
        carry3 = (s3 + (1 shl 20)) shr 21
        s4 += carry3
        s3 -= carry3  shl  21
        carry5 = (s5 + (1 shl 20)) shr 21
        s6 += carry5
        s5 -= carry5  shl  21
        carry7 = (s7 + (1 shl 20)) shr 21
        s8 += carry7
        s7 -= carry7  shl  21
        carry9 = (s9 + (1 shl 20)) shr 21
        s10 += carry9
        s9 -= carry9  shl  21
        carry11 = (s11 + (1 shl 20)) shr 21
        s12 += carry11
        s11 -= carry11  shl  21

        s0 += s12 * 666643
        s1 += s12 * 470296
        s2 += s12 * 654183
        s3 -= s12 * 997805
        s4 += s12 * 136657
        s5 -= s12 * 683901
        s12 = 0

        carry0 = s0 shr 21
        s1 += carry0
        s0 -= carry0  shl  21
        carry1 = s1 shr 21
        s2 += carry1
        s1 -= carry1  shl  21
        carry2 = s2 shr 21
        s3 += carry2
        s2 -= carry2  shl  21
        carry3 = s3 shr 21
        s4 += carry3
        s3 -= carry3  shl  21
        carry4 = s4 shr 21
        s5 += carry4
        s4 -= carry4  shl  21
        carry5 = s5 shr 21
        s6 += carry5
        s5 -= carry5  shl  21
        carry6 = s6 shr 21
        s7 += carry6
        s6 -= carry6  shl  21
        carry7 = s7 shr 21
        s8 += carry7
        s7 -= carry7  shl  21
        carry8 = s8 shr 21
        s9 += carry8
        s8 -= carry8  shl  21
        carry9 = s9 shr 21
        s10 += carry9
        s9 -= carry9  shl  21
        carry10 = s10 shr 21
        s11 += carry10
        s10 -= carry10  shl  21
        carry11 = s11 shr 21
        s12 += carry11
        s11 -= carry11  shl  21

        s0 += s12 * 666643
        s1 += s12 * 470296
        s2 += s12 * 654183
        s3 -= s12 * 997805
        s4 += s12 * 136657
        s5 -= s12 * 683901

        carry0 = s0 shr 21
        s1 += carry0
        s0 -= carry0  shl  21
        carry1 = s1 shr 21
        s2 += carry1
        s1 -= carry1  shl  21
        carry2 = s2 shr 21
        s3 += carry2
        s2 -= carry2  shl  21
        carry3 = s3 shr 21
        s4 += carry3
        s3 -= carry3  shl  21
        carry4 = s4 shr 21
        s5 += carry4
        s4 -= carry4  shl  21
        carry5 = s5 shr 21
        s6 += carry5
        s5 -= carry5  shl  21
        carry6 = s6 shr 21
        s7 += carry6
        s6 -= carry6  shl  21
        carry7 = s7 shr 21
        s8 += carry7
        s7 -= carry7  shl  21
        carry8 = s8 shr 21
        s9 += carry8
        s8 -= carry8  shl  21
        carry9 = s9 shr 21
        s10 += carry9
        s9 -= carry9  shl  21
        carry10 = s10 shr 21
        s11 += carry10
        s10 -= carry10  shl  21

        ret[0] = (s0 shr 0).toUInt8()
        ret[1] = (s0 shr 8).toUInt8()
        ret[2] = ((s0 shr 16) or (s1  shl  5)).toUInt8()
        ret[3] = (s1 shr 3).toUInt8()
        ret[4] = (s1 shr 11).toUInt8()
        ret[5] = ((s1 shr 19) or (s2  shl  2)).toUInt8()
        ret[6] = (s2 shr 6).toUInt8()
        ret[7] = ((s2 shr 14) or (s3  shl  7)).toUInt8()
        ret[8] = (s3 shr 1).toUInt8()
        ret[9] = (s3 shr 9).toUInt8()
        ret[10] = ((s3 shr 17) or (s4  shl  4)).toUInt8()
        ret[11] = (s4 shr 4).toUInt8()
        ret[12] = (s4 shr 12).toUInt8()
        ret[13] = ((s4 shr 20) or (s5  shl  1)).toUInt8()
        ret[14] = (s5 shr 7).toUInt8()
        ret[15] = ((s5 shr 15) or (s6  shl  6)).toUInt8()
        ret[16] = (s6 shr 2).toUInt8()
        ret[17] = (s6 shr 10).toUInt8()
        ret[18] = ((s6 shr 18) or (s7  shl  3)).toUInt8()
        ret[19] = (s7 shr 5).toUInt8()
        ret[20] = (s7 shr 13).toUInt8()
        ret[21] = (s8 shr 0).toUInt8()
        ret[22] = (s8 shr 8).toUInt8()
        ret[23] = ((s8 shr 16) or (s9  shl  5)).toUInt8()
        ret[24] = (s9 shr 3).toUInt8()
        ret[25] = (s9 shr 11).toUInt8()
        ret[26] = ((s9 shr 19) or (s10  shl  2)).toUInt8()
        ret[27] = (s10 shr 6).toUInt8()
        ret[28] = ((s10 shr 14) or (s11  shl  7)).toUInt8()
        ret[29] = (s11 shr 1).toUInt8()
        ret[30] = (s11 shr 9).toUInt8()
        ret[31] = (s11 shr 17).toUInt8()

        return ret
    }
}