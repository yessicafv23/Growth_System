package com.Growth_System.Controller;

import com.Growth_System.Entity.Inventario;
import com.Growth_System.Entity.Producto;
import com.Growth_System.Entity.TipoProducto;
import com.Growth_System.Entity.Usuario;
import com.Growth_System.Repository.InventarioRepository;
import com.Growth_System.Repository.ProductoRepository;
import com.Growth_System.Repository.TipoProductoRepository;
import com.Growth_System.Service.InventarioService;
import com.Growth_System.Service.ProductoService;
import com.Growth_System.Service.TipoProductoService;
import com.Growth_System.Service.UsuarioPersonalizadoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class InventarioController {

    final ProductoRepository prodRepository;
    final ProductoService prodService;
    final InventarioRepository invRepository;
    final InventarioService invService;
    final TipoProductoRepository tipoRepository;
    final TipoProductoService tipoService;
    final UsuarioPersonalizadoService usuarioPersonalizadoService;

    public InventarioController(ProductoRepository prodRepository,
                                ProductoService prodService,
                                InventarioRepository invRepository,
                                InventarioService invService,
                                TipoProductoRepository tipoRepository,
                                TipoProductoService tipoService, UsuarioPersonalizadoService usuarioPersonalizadoService) {
        this.prodRepository = prodRepository;
        this.prodService = prodService;
        this.invRepository = invRepository;
        this.invService = invService;
        this.tipoRepository = tipoRepository;
        this.tipoService = tipoService;
        this.usuarioPersonalizadoService = usuarioPersonalizadoService;
    }

    // ============ SECCION ADMIN ============ //

    //  =====  PRODUCTOS =====
    @GetMapping("/Admin/productos")
    public String getProductos(Model model, Producto producto, TipoProducto tipoProducto, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listProductos", this.prodService.listaProductos());
        model.addAttribute("inventarioAP", this.invService.getAllInventarios());
        model.addAttribute("tipoAP", this.tipoService.getAllTipoP());
        model.addAttribute("totalProductos", this.prodService.totalProductos());
        return "system/1-Administrador/modules/inventario/producto/productos";
    }

    @GetMapping("/Admin/editarProducto")
    public String editarProducto(Producto producto, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        producto = this.prodService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        model.addAttribute("inventarioAP", this.invService.getAllInventarios());
        model.addAttribute("tipoAP", this.tipoService.getAllTipoP());
        return "system/1-Administrador/modules/inventario/producto/productoEditar";
    }

    @GetMapping("/Admin/retirarProductos")
    public String retirarProducto(Producto producto, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        producto = this.prodService.encontrarProducto(producto);
        model.addAttribute("productoSalida", producto);
        return "system/1-Administrador/modules/inventario/producto/productoRetirar";
    }

    @PostMapping("/Admin/retirarProductos")
    public String agregarSalidas(Producto producto, RedirectAttributes attributes, Authentication authentication, Errors errores){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error producto no registrado");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/productos";
        }
        int cantidadRetirar = producto.getSalidasProd();
        producto = this.prodService.encontrarProducto(producto);
        if(cantidadRetirar > producto.getExistenciasProd()){
            attributes.addFlashAttribute("mensaje", "Digite un numero menor a las existencias");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/productos";
        }
        cantidadRetirar = (producto.getSalidasProd() + cantidadRetirar);
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        producto.setSalidasProd(cantidadRetirar);
        producto.setpUsuarioLast(usuario);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Producto Retirado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/productos";
    }

    @PostMapping("/Admin/entradasProducto")
    public String agregarEntradas(Producto producto, RedirectAttributes attributes, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        int cantidadEntrada = producto.getEntradasProd();
        producto = this.prodService.encontrarProducto(producto);
        int cantidadEntradaTotal = (producto.getEntradasProd() + cantidadEntrada);
        producto.setEntradasProd(cantidadEntradaTotal);
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        producto.setpUsuarioLast(usuario);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Se agrego " + cantidadEntrada + " Productos: " + producto.getNombreProd());
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/productos";
    }

    @PostMapping("/Admin/guardarProducto")
    public String agregarProducto(@Valid Producto producto, Errors errores, Authentication authentication, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error producto no registrado");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/productos";
        }

        if(producto.getSalidasProd() > producto.getEntradasProd()){
            attributes.addFlashAttribute("mensaje", "Digite un numero menor a las existencias");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/productos";
        }
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        if (this.prodService.hayProducto(producto)) {
            Usuario usuarioCreador = this.prodService.encontrarProducto(producto).getpUsuario();
            producto.setpUsuario(usuarioCreador);
            producto.setpUsuarioLast(usuario);
        } else {
            producto.setpUsuario(usuario);
            producto.setpUsuarioLast(usuario);
        }
        producto.setActivoProd(1);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Producto Registrado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/productos";
    }

    @GetMapping("/Admin/desactivarProducto")
    public String desactivarProducto(Producto producto, RedirectAttributes attributes, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        this.prodService.cambiarActivo(producto);
        attributes.addFlashAttribute("mensaje", "Producto desactivado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/productos";
    }

    //  =====  PRODUCTOS =====


    //  =====  TIPO PRODUCTOS =====

    @GetMapping("/Admin/tipoProducto")
    public String getTipos(Model model, TipoProducto tipo, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listTipoP", this.tipoService.getAllTipoP());
        return "system/1-Administrador/modules/inventario/tipo_producto/tipo-producto";
    }

    @PostMapping("/Admin/guardarTipoProducto")
    public String agregarTipoProducto(TipoProducto tipo, Errors errores, Authentication authentication, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        System.out.println(tipo);
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error al guardar el tipo");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/tipoProducto";
        }
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        if (this.tipoService.hayProducto(tipo)) {
            Usuario usuarioCreador = this.tipoService.encontrarTipo(tipo).getpUsuario();
            tipo.setpUsuario(usuarioCreador);
        } else {
            tipo.setpUsuario(usuario);
        }
        tipo.setActivoTipo(1);
        this.tipoService.guardar(tipo);
        attributes.addFlashAttribute("mensaje", "Tipo de producto Registrado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/tipoProducto";
    }

    @GetMapping("/Admin/desactivarTipoProducto")
    public String eliminarTipoProducto(TipoProducto tipo, RedirectAttributes attributes, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        this.tipoService.cambiarActivo(tipo);
        attributes.addFlashAttribute("mensaje", "Tipo de producto desactivado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/tipoProducto";
    }

    @GetMapping("/Admin/editarTipoProducto")
    public String editarTipoProducto(TipoProducto tipo, Model model, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        tipo = this.tipoService.encontrarTipo(tipo);
        model.addAttribute("tipo", tipo);
        model.addAttribute("tipoAP", this.tipoService.getAllTipoP());
        return "system/1-Administrador/modules/inventario/tipo_producto/tipoEditar";
    }

    //  =====  TIPO PRODUCTOS =====

    //  =====  INVENTARIO =====

    @GetMapping("/Admin/inventario")
    public String getInventario(Inventario inventario, Model model, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listInventario", this.invService.getAllInventarios());
        return "system/1-Administrador/modules/inventario/inventario/inventario";
    }

    @PostMapping("/Admin/guardarInventario")
    public String agregarInventario(Inventario inventario, Errors errores, Authentication authentication, RedirectAttributes attributes){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error al guardar el inventario");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/inventario";
        }
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        if (this.invService.hayProducto(inventario)) {
            Usuario usuarioCreador = this.invService.encontrarInventario(inventario).getpUsuario();
            inventario.setpUsuario(usuarioCreador);
        } else {
            inventario.setpUsuario(usuario);
        }
        inventario.setActivoInv(1);
        this.invService.guardar(inventario);
        attributes.addFlashAttribute("mensaje", "Inventario Registrado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/inventario";
    }

    @GetMapping("/Admin/desactivarInventario")
    public String eliminarInventario(Inventario inventario, RedirectAttributes attributes, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        this.invService.cambiarActivo(inventario);
        attributes.addFlashAttribute("mensaje", "Inventario Desactivado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/inventario";
    }

    @GetMapping("/Admin/editarInventario")
    public String editarInventario(Inventario inventario, Model model, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        inventario = this.invService.encontrarInventario(inventario);
        model.addAttribute("inventario", inventario);
        model.addAttribute("inventarioAP", this.invService.getAllInventarios());
        return "system/1-Administrador/modules/inventario/inventario/inventarioEditar";
    }

    //  =====  INVENTARIO =====

    // ============ SECCION ADMIN ============ //


    // ============ SECCION EMPLEADO ============ //

    //  =====  PRODUCTOS =====
    @GetMapping("/Empleado/productos")
    public String getProductosE(Model model, Producto producto, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listProductos", this.prodService.listaProductos());
        model.addAttribute("inventarioAP", this.invService.getAllInventarios());
        model.addAttribute("tipoAP", this.tipoService.getAllTipoP());
        model.addAttribute("totalProductos", this.prodService.totalProductos());
        return "system/2-Empleado/modules/inventario/producto/productos";
    }

    @GetMapping("/Empleado/editarProducto")
    public String editarProductoE(Producto producto, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        producto = this.prodService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        model.addAttribute("inventarioAP", this.invService.getAllInventarios());
        model.addAttribute("tipoAP", this.tipoService.getAllTipoP());
        return "system/2-Empleado/modules/inventario/producto/productoEditar";
    }

    @GetMapping("/Empleado/retirarProductos")
    public String retirarProductoE(Producto producto, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        producto = this.prodService.encontrarProducto(producto);
        model.addAttribute("productoSalida", producto);
        return "system/2-Empleado/modules/inventario/producto/productoRetirar";
    }

    @PostMapping("/Empleado/retirarProductos")
    public String agregarSalidasE(Producto producto, RedirectAttributes attributes, Authentication authentication, Errors errores){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error producto no registrado");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Empleado/productos";
        }
        int cantidadRetirar = producto.getSalidasProd();
        producto = this.prodService.encontrarProducto(producto);
        if(cantidadRetirar > producto.getExistenciasProd()){
            attributes.addFlashAttribute("mensaje", "Digite un numero menor a las existencias");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Empleado/productos";
        }
        cantidadRetirar = (producto.getSalidasProd() + cantidadRetirar);
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        producto.setSalidasProd(cantidadRetirar);
        producto.setpUsuarioLast(usuario);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Producto Retirado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Empleado/productos";
    }

    @PostMapping("/Empleado/entradasProducto")
    public String agregarEntradasE(Producto producto, RedirectAttributes attributes, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        int cantidadEntrada = producto.getEntradasProd();
        producto = this.prodService.encontrarProducto(producto);
        int cantidadEntradaTotal = (producto.getEntradasProd() + cantidadEntrada);
        producto.setEntradasProd(cantidadEntradaTotal);
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        producto.setpUsuarioLast(usuario);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Se agrego " + cantidadEntrada + " Productos: " + producto.getNombreProd());
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Empleado/productos";
    }

    @PostMapping("/Empleado/guardarProducto")
    public String agregarProductoE(@Valid Producto producto, Errors errores, Authentication authentication, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error producto no registrado");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Empleado/productos";
        }
        String emailAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(emailAuth);
        if (this.prodService.hayProducto(producto)) {
            Usuario usuarioCreador = this.prodService.encontrarProducto(producto).getpUsuario();
            producto.setpUsuario(usuarioCreador);
            producto.setpUsuarioLast(usuario);
        } else {
            producto.setpUsuario(usuario);
            producto.setpUsuarioLast(usuario);
        }
        producto.setActivoProd(1);
        this.prodService.guardar(producto);
        attributes.addFlashAttribute("mensaje", "Producto Registrado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Empleado/productos";
    }

    @GetMapping("/Empleado/desactivarProducto")
    public String desactivarProductoE(Producto producto, RedirectAttributes attributes, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        this.prodService.cambiarActivo(producto);
        attributes.addFlashAttribute("mensaje", "Producto desactivado");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Empleado/productos";
    }

    //  =====  PRODUCTOS =====


    //  =====  TIPO PRODUCTOS =====

    @GetMapping("/Empleado/tipoProducto")
    public String getTiposE(Model model, TipoProducto tipo, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listTipoP", this.tipoService.getAllTipoP());
        return "system/2-Empleado/modules/inventario/tipo_producto/tipo-producto";
    }

    //  =====  TIPO PRODUCTOS =====

    //  =====  INVENTARIO =====

    @GetMapping("/Empleado/inventario")
    public String getInventarioE(Inventario inventario, Model model, Authentication authentication){
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("listInventario", this.invService.getAllInventarios());
        return "system/2-Empleado/modules/inventario/inventario/inventario";
    }

    // ============ SECCION EMPLEADO ============ //
}

