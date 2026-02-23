<?php
class Conexion extends PDO
{
    private const SERVIDOR_BD = 'localhost';
    private const USUARIO_BD = 'root';
    private const PASSWORD_BD = '';
    private const BD = 'clientes';
    private const DSN = "mysql:host=" . self::SERVIDOR_BD . ";dbname=" . self::BD . ';charset=utf8mb4';

    public function __construct()
    {
        parent::__construct(self::DSN, self::USUARIO_BD, self::PASSWORD_BD);
    }

    //aquí los métodos para el tratamiento de los datos

    public function getClientes() {
        $sql = "select * from clientes";
        $stmt = $this->query($sql);
        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }

    function getCliente($codCliente = '')
    {
        $sql = "select * from clientes where codCliente = ?";
        $stmt = $this->prepare($sql);
        $stmt->execute([$codCliente]);
        return $stmt->fetchObject(); // o $stmt->fetch(PDO::FETCH_OBJ);
    }

    function insertarCliente($nombre, $codProvincia, $vip): int {
        $sql = "insert into clientes (nombre, codProvincia, vip) values (?, ?, ?)";
        $stmt = $this->prepare($sql);
        $stmt->execute([$nombre, $codProvincia, $vip]);
        return $this->lastInsertId();
    }

    function insertarProvincia($nombre): int {
        $sql = "insert into provincias (nombre) values (?)";
        $stmt = $this->prepare($sql);
        $stmt->execute([$nombre]);
        return $this->lastInsertId();
    }
}

try {
    $con = new Conexion();
} catch (PDOException $ex) {
    exit("Problemas: {$ex->getMessage()}");
}
?>