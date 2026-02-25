<?php
class Conexion extends PDO
{
    private const SERVIDOR_BD = 'localhost';
    private const USUARIO_BD = 'root';
    private const PASSWORD_BD = '';
    private const BD = 'hotel';
    private const DSN = "mysql:host=" . self::SERVIDOR_BD . ";dbname=" . self::BD . ';charset=utf8mb4';

    public function __construct()
    {
        parent::__construct(self::DSN, self::USUARIO_BD, self::PASSWORD_BD);
    }

    //aquí los métodos para el tratamiento de los datos

    public function getHabitaciones() {
        $sql = "select * from habitaciones";
        $stmt = $this->prepare($sql);
        $stmt->execute();
        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }

    public function getReservasPorHabitacion($idHabitacion) {
        $sql = "select * from reservas r where r.idhabitacion = ?";
        $stmt = $this->prepare($sql);
        $stmt->execute([$idHabitacion]);
        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }

    function insertarReserva($idHabitacion, $nombre, $dia, $numDias): int {

        $sqlChk = "select dia, numDias from reservas where idHabitacion = ? and dia = ?";
        $stmt = $this->prepare($sqlChk);
        $stmt->execute([$idHabitacion, $dia]);

        if ($stmt->rowCount() != 0) {
            return -1;
        }

        $sql = "insert into reservas (idHabitacion, nombre, dia, numDias) values (?, ?, ?, ?)";
        $stmt = $this->prepare($sql);
        $stmt->execute([$idHabitacion, $nombre, $dia, $numDias]);
        return $this->lastInsertId();
    }

    function deleteReserva($idReserva): bool {
        $sql = "delete from reservas where codReserva = ?";
        $stmt = $this->prepare($sql);
        return $stmt->execute([$idReserva]);
    }
}

try {
    $con = new Conexion();
} catch (PDOException $ex) {
    exit("Problemas: {$ex->getMessage()}");
}
?>