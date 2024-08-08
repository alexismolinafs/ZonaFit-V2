package gm.zona_fit.service;

import gm.zona_fit.model.Cliente;

import java.util.List;

public interface IClienteServ {

    public List<Cliente> listarCliente();

    public Cliente buscarClienteId(Integer idCliente);

    public void saveCliente(Cliente cliente);

    public void eliminarCliente(Integer idCliente);
}