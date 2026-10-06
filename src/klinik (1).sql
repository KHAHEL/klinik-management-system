-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 20 Nov 2025 pada 11.16
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klinik`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` bigint(10) NOT NULL,
  `id_pengguna` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gaji` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `id_pengguna`, `department`, `shift`, `email`, `gaji`) VALUES
(1, 9, 'Keuangan', 'Pagi', 'admin@klinik.com', 5000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `id_dokter` bigint(10) NOT NULL,
  `id_pengguna` bigint(20) NOT NULL,
  `spesialisasi` varchar(255) DEFAULT NULL,
  `gaji` int(30) NOT NULL,
  `noRegis` varchar(255) NOT NULL,
  `no_regis` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dokter`
--

INSERT INTO `dokter` (`id_dokter`, `id_pengguna`, `spesialisasi`, `gaji`, `noRegis`, `no_regis`) VALUES
(1, 7, 'Umum', 7000000, 'REG12345', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id_pengguna` bigint(20) NOT NULL,
  `keluhan` varchar(255) NOT NULL,
  `riwayatMedis` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id_pengguna`, `keluhan`, `riwayatMedis`) VALUES
(3, 'Batuk-batuk', 'Alergi Debu'),
(10, 'fasd', 'agawsda'),
(11, 'kjuhh', 'iuhuo');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` bigint(20) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `umur` int(10) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `noTelp` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `no_telp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama`, `umur`, `gender`, `alamat`, `noTelp`, `username`, `password`, `role`, `no_telp`) VALUES
(2, 'Siti Perawat', 28, 'P', 'Bantul', '821345678', 'perawat01', '78910', 'perawat', NULL),
(3, 'Joko Pasien', 22, 'L', 'Jogja', '812345678', 'joko22', '12345', 'pasien', NULL),
(7, 'Dr. Budi', 40, 'L', 'Sleman', '812345678', 'dokter01', 'abc123', 'dokter', NULL),
(9, 'Admin Klinik', 30, 'L', 'Jogja', '812334455', 'admin01', 'adminpass', 'admin', NULL),
(10, 'Sanjiro Ozara', 21, 'L', 'dasdw', '08954332567', 'SANJIRO70', '123123123', 'pasien', NULL),
(11, 'Sanjiro Ozara', 65, 'L', 'igyutfviu', '08954332567', 'wilsahen', '123123123', 'pasien', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `perawat`
--

CREATE TABLE `perawat` (
  `id_perawat` bigint(10) NOT NULL,
  `id_pengguna` bigint(20) NOT NULL,
  `ruangKerja` varchar(255) DEFAULT NULL,
  `gaji` int(30) NOT NULL,
  `ruang_kerja` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `perawat`
--

INSERT INTO `perawat` (`id_perawat`, `id_pengguna`, `ruangKerja`, `gaji`, `ruang_kerja`) VALUES
(0, 2, 'UGD', 3500000, NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD KEY `FK2b60avbubdukmub1agdv10ipu` (`id_pengguna`);

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_dokter`),
  ADD UNIQUE KEY `UKp0mqvu22xyvhl6wfqt7g8qxb5` (`no_regis`),
  ADD KEY `FKfidom0sd2payx4jupfayk9gq8` (`id_pengguna`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD KEY `FKeh2r6ugdisfb8uuh6p1vvqcxt` (`id_pengguna`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `perawat`
--
ALTER TABLE `perawat`
  ADD PRIMARY KEY (`id_perawat`),
  ADD KEY `FKa8t5o01edcn83pspatjdww3le` (`id_pengguna`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK2b60avbubdukmub1agdv10ipu` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `FKfidom0sd2payx4jupfayk9gq8` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD CONSTRAINT `FKeh2r6ugdisfb8uuh6p1vvqcxt` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `perawat`
--
ALTER TABLE `perawat`
  ADD CONSTRAINT `FKa8t5o01edcn83pspatjdww3le` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
