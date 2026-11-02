package com.example.maituananh_2415141122125.helper

import vn.edu.ute.datphong.data.DuLieuMau
import vn.edu.ute.datphong.model.DonDatPhong
import vn.edu.ute.datphong.model.LoaiPhong

object DatPhongHelper {

    fun timLoaiPhong(ma: String): LoaiPhong? {
        val maCanTim = ma.trim()
        return DuLieuMau.dsLoaiPhong.firstOrNull { it.ma.equals(maCanTim, ignoreCase = true) }
    }

    fun hopLeSoKhach(loai: LoaiPhong, soKhach: Int): Boolean {
        return soKhach in 1..(loai.sucChua + 2)
    }

    fun tinhTienPhong_2415141122125(loai: LoaiPhong, soDem: Int, soKhach: Int): Long {
        if (soDem < 1 || !hopLeSoKhach(loai, soKhach)) return 0L

        var tienPhong = loai.giaMotDem * soDem
        if (soDem >= 5) {
            tienPhong = tienPhong * 85 / 100
        }

        val soKhachVuot = maxOf(0, soKhach - loai.sucChua)
        val phuThu = soKhachVuot * 150000L * soDem

        return tienPhong + phuThu
    }

    fun taoMaDon(don: DonDatPhong): String {
        val phan = don.ngayNhan.trim().split("/")
        val ngay = phan.getOrElse(0) { "" }
        val thang = phan.getOrElse(1) { "" }
        return "DP125-" + ngay + thang + "-" + don.maLoaiPhong.trim().uppercase()
    }

    fun locTheoSoKhach(ds: List<LoaiPhong>, soKhach: Int): List<LoaiPhong> {
        return ds.filter { it.sucChua >= soKhach }
            .sortedWith(
                compareByDescending<LoaiPhong> { it.sucChua }
                    .thenBy { it.giaMotDem }
            )
    }
}