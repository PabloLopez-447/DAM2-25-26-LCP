<?php
require 'router.php';
require 'conexionBD.php';
header('Content-Type: application/json');
$router = new Router();

$router->get('/habitaciones', function () {
    global $con;
    return ['body' => json_encode($con->getHabitaciones())];
});

$router->get('/reservas/([\w]+)', function ($params) {
    global $con;
    $idHabitacion = $params[1];
    if ($con -> getReservasPorHabitacion($idHabitacion) != null){
        return ['body' => json_encode($con->getReservasPorHabitacion($idHabitacion))];
    }
    return ['status' => 404, 'body' => json_encode(['message' => 'Habitacion Not Found'])];
    
});

$router->post('/reservas', function () {
    global $con;
    parse_str(file_get_contents('php://input', true), $datos);
    $idHabitacion = $datos['idHabitacion'];
    $nombre = $datos['nombre'];
    $dia = $datos['dia'];
    $numDias = $datos['numDias'];

    if (($idReserva = $con->insertarReserva($idHabitacion, $nombre, $dia, $numDias)) != null) {
        if($idReserva == -1){
            return ['status' => 406, 'body' => json_encode(['message' => 'Datos incorrectos'])];
        }
        return ['status' => 201, 'body' => json_encode(['id' => $idReserva])];
    }
    return ['status' => 400, 'body' => json_encode(['message' => 'Datos incorrectos'])];
});

$router->delete('/reservas/([\w]+)', function ($params) {
    global $con;
    $idReserva = $params[1];
    if ($con->deleteReserva($idReserva))
        return ['status' => 204];
    return ['status' => 404, 'body' => json_encode(['message' => 'Reserva Not Found'])];
});

$router->dispatch($_SERVER['REQUEST_METHOD'], $_SERVER['PATH_INFO']);
?>