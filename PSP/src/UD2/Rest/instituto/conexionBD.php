<?php
class Conexion extends PDO
{
    private const SERVIDOR_BD = 'localhost';
    private const USUARIO_BD = 'root';
    private const PASSWORD_BD = '';
    private const BD = 'instituto';
    private const DSN = "mysql:host=" . self::SERVIDOR_BD . ";dbname=" . self::BD . ';charset=utf8mb4';

    public function __construct()
    {
        parent::__construct(self::DSN, self::USUARIO_BD, self::PASSWORD_BD);
    }

    //aquí los métodos para el tratamiento de los datos

    public function getAlumnosPorCurso($nombreCurso) {
        $sql = "select * from alumno a join curso c on a.idcurso = c.cod where c.nombre = ?";
        $stmt = $this->prepare($sql);
        $stmt->execute([$nombreCurso]);
        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }

    function deleteCurso($nombreCurso): bool {
        $sql = "delete from curso where nombre = ?";
        $stmt = $this->prepare($sql);
        return $stmt->execute([$nombreCurso]);// o $stmt->fetch(PDO::FETCH_OBJ);
    }

    function insertarCurso($nombre, $aforo): int {
        $sql = "insert into curso (nombre, aforo) values (?, ?)";
        $stmt = $this->prepare($sql);
        $stmt->execute([$nombre, $aforo]);
        return $this->lastInsertId();
    }

    function insertarAlumno($nombre, $idCurso): int {
        $sql = "insert into alumno (nombre, idCurso) values (?, ?)";
        $stmt = $this->prepare($sql);
        $stmt->execute([$nombre, $idCurso]);
        return $this->lastInsertId();
    }
}

try {
    $con = new Conexion();
} catch (PDOException $ex) {
    exit("Problemas: {$ex->getMessage()}");
}
?>