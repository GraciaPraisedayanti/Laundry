-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jun 2020 pada 14.23
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_cucian`
--

CREATE TABLE `jenis_cucian` (
  `kd_jenis` int(11) NOT NULL,
  `jenis_cucian` varchar(30) CHARACTER SET latin1 NOT NULL,
  `harga` int(110) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jenis_cucian`
--

INSERT INTO `jenis_cucian` (`kd_jenis`, `jenis_cucian`, `harga`) VALUES
(1, 'cuci kering', 4000),
(2, 'Setrika', 6000),
(3, 'Cuci + Setrika', 8000),
(4, 'Cuci Boneka', 5000),
(5, 'Cuci Sepatu', 3000),
(6, 'Cuci Celana', 5000),
(7, 'Cuci Sprei', 6000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(30) NOT NULL,
  `nama_pelanggan` varchar(40) CHARACTER SET latin1 NOT NULL,
  `alamat` varchar(50) CHARACTER SET latin1 NOT NULL,
  `telp` varchar(15) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`, `telp`) VALUES
(1, 'Bagus', 'Sriwijaya', '082132620253'),
(2, 'Prabas', 'Rambi', '082145783408'),
(3, 'Ayis', 'Mangli', '089764357846');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penerimaan`
--

CREATE TABLE `penerimaan` (
  `no_order` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(40) CHARACTER SET latin1 NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_keluar` date NOT NULL,
  `t_bayar` int(20) NOT NULL,
  `bayar` int(20) NOT NULL,
  `sisa` int(20) NOT NULL,
  `status` varchar(20) CHARACTER SET latin1 NOT NULL,
  `kd_jenis` int(11) NOT NULL,
  `jenis_cucian` varchar(40) CHARACTER SET latin1 NOT NULL,
  `harga` int(11) NOT NULL,
  `berat` int(11) NOT NULL,
  `keterangan` varchar(200) CHARACTER SET latin1 NOT NULL,
  `pengerjaan` varchar(20) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penerimaan`
--

INSERT INTO `penerimaan` (`no_order`, `id_pelanggan`, `nama_pelanggan`, `tgl_masuk`, `tgl_keluar`, `t_bayar`, `bayar`, `sisa`, `status`, `kd_jenis`, `jenis_cucian`, `harga`, `berat`, `keterangan`, `pengerjaan`) VALUES
(1, 1, 'Bagus', '2020-06-05', '2020-06-08', 8000, 8000, 0, 'Lunas', 1, 'cuci kering', 4000, 2, '', 'Diambil'),
(3, 2, 'Prabas', '2020-06-05', '2020-06-10', 30000, 20000, -10000, 'Lunas', 2, 'Setrika', 6000, 5, '', 'Diambil'),
(4, 3, 'Ayis', '2020-06-06', '2020-06-13', 40000, 20000, -20000, 'Lunas', 3, 'Cuci + Setrika', 8000, 5, '', 'Diambil');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(5) NOT NULL,
  `username` varchar(20) CHARACTER SET latin1 NOT NULL,
  `password` varchar(20) CHARACTER SET latin1 NOT NULL,
  `hakakses` enum('admin','user','','') CHARACTER SET latin1 NOT NULL,
  `nama_pengguna` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `username`, `password`, `hakakses`, `nama_pengguna`) VALUES
(1, 'Gracia', 'Praise', 'user', 'Gracia Andhita'),
(2, 'E31192304', 'mif2020', 'admin', 'Gracia Andhita P.B');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `no_transaksi` int(11) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `no_order` int(11) NOT NULL,
  `dibayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`no_transaksi`, `tgl_transaksi`, `no_order`, `dibayar`, `kembalian`) VALUES
(1, '2020-06-05', 1, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `jenis_cucian`
--
ALTER TABLE `jenis_cucian`
  ADD PRIMARY KEY (`kd_jenis`) USING BTREE,
  ADD KEY `id_jenis` (`kd_jenis`) USING BTREE;

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `penerimaan`
--
ALTER TABLE `penerimaan`
  ADD PRIMARY KEY (`no_order`),
  ADD KEY `id_pelanggan` (`id_pelanggan`) USING BTREE,
  ADD KEY `kd_jenis` (`kd_jenis`) USING BTREE;

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD KEY `no_order` (`no_order`) USING BTREE;

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `jenis_cucian`
--
ALTER TABLE `jenis_cucian`
  MODIFY `kd_jenis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `penerimaan`
--
ALTER TABLE `penerimaan`
  MODIFY `no_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `no_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
