<?php
require 'conexionBD.php';

//$verbo = $_SERVER['REQUEST_METHOD'];  es el verbo indicado por el cliente
//$uri = $_SERVER['PATH_INFO'] ?? '';  es la ruta que indica el recurso sobre el que actuaremos

$verbo = $_SERVER['REQUEST_METHOD'];
$pathInfo = isset($_SERVER['PATH_INFO']) ? trim($_SERVER['PATH_INFO'], '/') : '';
$rutas = $pathInfo == '' ? [] : explode('/', $pathInfo);

$items = count($rutas);
if ($items == 0) {
    http_response_code(404);
    exit;
}
if ($verbo == 'GET') {
    // manejar las peticiones GET
    if ($rutas[0] == 'clientes') {
        switch ($items) {
            case 1:
                $datos = $con->getClientes();
                echo json_encode($datos);
                break;
            case 2:
                $codCliente = $rutas[1];
                if (!preg_match('/^\d+$/', $codCliente)) {
                    http_response_code(400);
                    exit;
                }
                $cliente = $con->getCliente($codCliente);
                if ($cliente == null) {
                    http_response_code(404);
                    exit;
                } else {
                    echo json_encode($cliente);
                }
                break;
        }
    }
} else if ($verbo == 'POST') {
    if ($rutas[0] == 'clientes') {
        // Manejar las peticiones POST
        $stringDatosCabecera = file_get_contents('php://input', true); // leer los datos enviados en el cuerpo de la petición
        parse_str($stringDatosCabecera, $datos); // convertir la cadena en un array asociativo
        //print_r($datos); // mostrar los datos recibidos (aquí iría la lógica para insertar en la base de datos)
        $nombre = $datos['nombre'];
        $codProvincia = $datos['codProvincia'];
        $vip = $datos['vip'];
        //TODO validar los datos recibidos

        if ($con->insertarCliente($nombre, $codProvincia, $vip)) {
            http_response_code(201); // Recurso creado
            exit;
        } else {
            http_response_code(422); // Bad Request
            exit;
        }
    }
    if ($rutas[0] == 'provincias') {
        $stringDatosCabecera = file_get_contents('php://input', true); // leer los datos enviados en el cuerpo de la petición
        parse_str($stringDatosCabecera, $datos); // convertir la cadena en un array asociativo
        $nombre = $datos['nombre'];
        if ($con->insertarProvincia($nombre)) {
            http_response_code(201); // Recurso creado
            exit;
        } else {
            http_response_code(422); // Bad Request
            exit;
        }
    }
} else {
    http_response_code(405); // Método no permitido
    exit;
}
?>