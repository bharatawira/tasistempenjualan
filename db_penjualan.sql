-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Jul 2021 pada 16.58
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
-- Database: `db_penjualan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `cupang`
--

CREATE TABLE `cupang` (
  `id_cupang` int(11) NOT NULL,
  `nama_cupang` text NOT NULL,
  `harga_cupang` text NOT NULL,
  `gambar_cupang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data untuk tabel `cupang`
--

INSERT INTO `cupang` (`id_cupang`, `nama_cupang`, `harga_cupang`, `gambar_cupang`) VALUES
(42, 'Cupang Halfmoon', '100000', 'Halfmoon.jpg'),
(43, 'Cupang Belgi Bangkok', '110000', 'Belgi Bangkok.jpg'),
(44, 'Cupang Big Ear', '120000', 'Big Ear.jpg'),
(45, 'Cupang Crown Tail', '130000', 'Crown Tail.jpg'),
(46, 'Cupang Emas', '140000', 'Emas.jpg'),
(47, 'Cupang Fancy', '150000', 'Fancy.jpg'),
(48, 'Cupang Giant', '160000', 'Giant.jpg'),
(49, 'Cupang Halfsun', '160000', 'Halfsun.jpg'),
(50, 'Cupang HMPK', '170000', 'HMPK.jpg'),
(51, 'Cupang Over Halfmoon', '180000', 'Over Halfmoon.jpg'),
(52, 'Cupang Paradise', '190000', 'Paradise.jpg'),
(53, 'Cupang Plakat', '290000', 'Plakat.jpg'),
(54, 'Cupang Sarawak', '250000', 'Sarawak.jpg');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_cupang` int(11) NOT NULL,
  `nama_pembeli` varchar(100) NOT NULL,
  `notelp_pembeli` varchar(100) NOT NULL,
  `alamat_pembeli` varchar(100) NOT NULL,
  `harga_penjualan` varchar(100) NOT NULL,
  `approve` varchar(100) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `id_user`, `id_cupang`, `nama_pembeli`, `notelp_pembeli`, `alamat_pembeli`, `harga_penjualan`, `approve`, `date`) VALUES
(51, 45, 53, 'wewe', '08876', '1asdsada', '12222222', 'true', '2021-07-04'),
(52, 45, 46, 'udin', '0895931236789', 'jl.ceger raya', '1500000', 'true', '2021-07-05'),
(53, 45, 52, 'udin', '085437589', 'jlsdjko', '200000', 'false', '2021-07-05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','user','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `password`, `role`) VALUES
(45, 'Wendi', 'wewen', '547ba58dbfd1a1cdbc0c391baff4160b91f741b4', 'user'),
(46, 'Bharata', 'bharatawira', '661d796484b61838da90c243ead8c69c390fa2b3', 'admin'),
(47, 'Naufal Ridho', 'naufalridho', '0e0bf5069559eb79ecdf1e04e00700cc09e6110f', 'admin'),
(48, 'Wedi', 'wedyrefry', '6acf93c3249fc42a3e82fc09b6b2989724ac5ab9', 'user'),
(49, 'Bani', 'babanini', '00b94308fc9bd130076f4c0d242575b41a5e3d51', 'user');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `cupang`
--
ALTER TABLE `cupang`
  ADD PRIMARY KEY (`id_cupang`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`) USING BTREE,
  ADD KEY `id_cupang` (`id_cupang`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `cupang`
--
ALTER TABLE `cupang`
  MODIFY `id_cupang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_penjualan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `penjualan_ibfk_2` FOREIGN KEY (`id_cupang`) REFERENCES `cupang` (`id_cupang`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
