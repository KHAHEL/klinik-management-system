-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Nov 2025 pada 18.40
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
  `id_pengguna` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gaji` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_pengguna`, `department`, `shift`, `email`, `gaji`) VALUES
(4, 'Front Office', 'Pagi', 'admin@klinik.com', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE `dokter` (
  `id_pengguna` bigint(20) NOT NULL,
  `spesialisasi` varchar(255) DEFAULT NULL,
  `gaji` double DEFAULT NULL,
  `noRegis` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dokter`
--

INSERT INTO `dokter` (`id_pengguna`, `spesialisasi`, `gaji`, `noRegis`) VALUES
(1, 'Umum', 5000000, 'REG-G01'),
(2, 'Anak', 7200000, 'REG-A01'),
(3, 'Umum', 7500000, 'REG-U01');

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
(5, 'Demam dan pusing', 'Tidak ada riwayat khusus');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pendaftaran_pasien`
--

CREATE TABLE `pendaftaran_pasien` (
  `id_daftar` bigint(20) NOT NULL,
  `id_pengguna` bigint(20) DEFAULT NULL,
  `poli` varchar(255) DEFAULT NULL,
  `noAntrian` int(11) DEFAULT NULL,
  `tglDaftar` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id_admin` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pendaftaran_pasien`
--

INSERT INTO `pendaftaran_pasien` (`id_daftar`, `id_pengguna`, `poli`, `noAntrian`, `tglDaftar`, `status`, `id_admin`) VALUES
(1, 5, 'Umum', 1, '2025-11-13', 'MENUNGGU', NULL),
(2, 5, 'Anak', 1, '2025-11-07', 'MENUNGGU', NULL),
(3, 5, 'Umum', 1, '2025-11-06', 'MENUNGGU', NULL),
(4, 5, 'Umum', 1, '2025-11-18', 'MENUNGGU', 4);

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
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama`, `umur`, `gender`, `alamat`, `noTelp`, `username`, `password`, `role`) VALUES
(1, 'Dr. Gigi Sari', 40, 'P', 'Klinik Utama', '0811111111', 'dokter_gigi', 'gigi123', 'dokter'),
(2, 'Dr. Anak Bima', 38, 'L', 'Klinik Utama', '0822222222', 'dokter_anak', 'anak123', 'dokter'),
(3, 'Dr. Umum Jaya', 45, 'L', 'Klinik Utama', '0833333333', 'dokter_umum', 'umum123', 'dokter'),
(4, 'Admin Klinik', 30, 'L', 'Klinik Pusat', '0844444444', 'admin01', 'admin123', 'admin'),
(5, 'Pasien Contoh', 21, 'P', 'Jaksel', '0855555555', 'pasien01', 'pasien123', 'pasien'),
(6, 'Perawat Lina', 28, 'P', 'Klaten', '0866666666', 'perawat01', 'perawat123', 'perawat');

-- --------------------------------------------------------

--
-- Struktur dari tabel `perawat`
--

CREATE TABLE `perawat` (
  `id_pengguna` bigint(20) NOT NULL,
  `ruangKerja` varchar(255) DEFAULT NULL,
  `gaji` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `perawat`
--

INSERT INTO `perawat` (`id_pengguna`, `ruangKerja`, `gaji`) VALUES
(6, 'Ruang Perawatan Umum', 4000000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD KEY `FKeh2r6ugdisfb8uuh6p1vvqcxt` (`id_pengguna`);

--
-- Indeks untuk tabel `pendaftaran_pasien`
--
ALTER TABLE `pendaftaran_pasien`
  ADD PRIMARY KEY (`id_daftar`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `FKpo2iyi43l942x6bne4qkrsd8g` (`id_admin`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indeks untuk tabel `perawat`
--
ALTER TABLE `perawat`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pendaftaran_pasien`
--
ALTER TABLE `pendaftaran_pasien`
  MODIFY `id_daftar` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `dokter_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD CONSTRAINT `FKeh2r6ugdisfb8uuh6p1vvqcxt` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `pendaftaran_pasien`
--
ALTER TABLE `pendaftaran_pasien`
  ADD CONSTRAINT `FK8k8cai52qbick287q81vmi6e5` FOREIGN KEY (`id_pengguna`) REFERENCES `pasien` (`id_pengguna`),
  ADD CONSTRAINT `FKpo2iyi43l942x6bne4qkrsd8g` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_pengguna`),
  ADD CONSTRAINT `pendaftaran_pasien_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Ketidakleluasaan untuk tabel `perawat`
--
ALTER TABLE `perawat`
  ADD CONSTRAINT `perawat_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
