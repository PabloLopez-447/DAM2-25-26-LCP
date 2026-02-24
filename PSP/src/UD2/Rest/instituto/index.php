<?php
require 'router.php';
require 'conexionBD.php';
header('Content-Type: application/json');
$router = new Router();
$router->get('/alumnos/([\w]+)', function ($params) {
    global $con;
    $nombreCurso = $params[1];
    return ['body' => json_encode($con->getAlumnosPorCurso($nombreCurso))];
});

$router->post('/alumnos', function () {
    //$c = json_decode(file_get_contents('php://input'), false);
    global $con;
    parse_str(file_get_contents('php://input', true), $datos);
    $nombre = $datos['nombre'];
    $idCurso = $datos['idCurso'];

    if (($idAlumno = $con->insertarAlumno($nombre, $idCurso)) != null) {
        return ['status' => 201, 'body' => json_encode(['id' => $idAlumno])];
    }
    return ['status' => 400, 'body' => json_encode(['message' => 'Datos incorrectos'])];
});

$router->post('/cursos', function () {
    //$c = json_decode(file_get_contents('php://input'), false);
    global $con;
    parse_str(file_get_contents('php://input', true), $datos);
    $nombre = $datos['nombre'];
    $aforo = $datos['aforo'];

    if (($idCurso = $con->insertarCurso($nombre, $aforo)) != null) {
        return ['status' => 201, 'body' => json_encode(['id' => $idCurso])];
    }
    return ['status' => 400, 'body' => json_encode(['message' => 'Datos incorrectos'])];
});

$router->delete('/cursos/([\w]+)', function ($params) {
    global $con;
    $nombreCurso = $params[1];
    if ($con->deleteCurso($nombreCurso))
        return ['status' => 204];
    return ['status' => 404, 'body' => json_encode(['message' => 'Cliente Not Found'])];
});

$router->dispatch($_SERVER['REQUEST_METHOD'], $_SERVER['PATH_INFO']);
?>