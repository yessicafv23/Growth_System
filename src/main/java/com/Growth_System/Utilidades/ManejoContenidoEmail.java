package com.Growth_System.Utilidades;

import org.springframework.stereotype.Component;

@Component
public class ManejoContenidoEmail {

    public String envioEmail(boolean envioClave, String link) {
        if ((envioClave && link == null) || (envioClave || link == null)) {
            String contenido =
                    "    <div style=\"padding:0;margin:0;font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\">\n" +
                            "        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" cellspacing=\"0\"\n" +
                            "            style=\"margin: 0 auto; padding: 0\" width=\"600\">\n" +
                            "            <tbody>\n" +
                            "                <tr>\n" +
                            "                    <td width=\"600\" height=\"90\" valign=\"top\" style=\"background: green\">\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" align=\"center\" cellspacing=\"0\" style=\"margin:0 auto;padding:0\"\n" +
                            "                            width=\"600\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"left\" width=\"150\" style=\"padding-left:56px;width:150px\">\n" +
                            "                                        <p style=\"font-size: 2rem; color: white\">Growth System</p>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"padding: 30px 0\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"center\" style=\"background:#fff;padding:0 56px;width:100%;\">\n" +
                            "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                            "                                            <tbody>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding: 0 0 15px 0\">Bienvenido [[name]]</td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding: 0\">Por favor verificar tu correo electronico,\n" +
                            "                                                        dando click en el siguiente enlace:</td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding:24px 0\">\n" +
                            "                                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"10\"\n" +
                            "                                                            align=\"center\">\n" +
                            "                                                            <tbody>\n" +
                            "                                                                <tr>\n" +
                            "                                                                    <td align=\"center\" bgcolor=\"black\"\n" +
                            "                                                                        style=\"color:#000000;font-size:14px;border-radius:4px; background: #F7931E;\"\n" +
                            "                                                                        valign=\"top\">\n" +
                            "                                                                        <font face=\"'Oracle Sans', sans-serif\">\n" +
                            "                                                                            <a style=\"display: inline-block; text-decoration: none; color: black; font-weight: bold;\" href=\"[[URL]]\" target=\"_self\">Verificar dirección de correo electrónico</a>\n" +
                            "                                                                        </font>\n" +
                            "                                                                    </td>\n" +
                            "                                                                </tr>\n" +
                            "                                                            </tbody>\n" +
                            "                                                        </table>\n" +
                            "\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding:0\">\n" +
                            "                                                        Gracias, <br>\n" +
                            "                                                        <strong style=\"color:#766f6e\">Equipo de Growth System</strong>\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                            </tbody>\n" +
                            "                                        </table>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td width=\"600\" height=\"40\" style=\"background: green;\">\n" +
                            "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                            "                                            <tbody>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"font-family:'Oracle Sans',sans-serif;font-size:12px;color:#ffffff;padding:15px 48px 0 56px\"\n" +
                            "                                                        width=\"100%\">Copyright @ 2021, Oracle y/o sus afiliadas. Todos\n" +
                            "                                                        los derechos reservados.</td>\n" +
                            "                                                </tr>\n" +
                            "\n" +
                            "                                                <tr>\n" +
                            "                                                    <td>\n" +
                            "                                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                            "                                                            <tbody>\n" +
                            "                                                                <tr>\n" +
                            "                                                                    <td\n" +
                            "                                                                        style=\"font-size:12px;color:#ccc;font-weight:bold;padding-top:0;padding-right:10px;padding-bottom:0;padding-left:56px\">\n" +
                            "                                                                    </td>\n" +
                            "                                                                </tr>\n" +
                            "                                                            </tbody>\n" +
                            "                                                        </table>\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                            </tbody>\n" +
                            "                                        </table>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "            </tbody>\n" +
                            "        </table>\n" +
                            "    </div>";
            return contenido;
        } else {
            String contenido =
                    "    <div style=\"padding:0;margin:0;font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\">\n" +
                            "        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" cellspacing=\"0\"\n" +
                            "            style=\"margin: 0 auto; padding: 0\" width=\"600\">\n" +
                            "            <tbody>\n" +
                            "                <tr>\n" +
                            "                    <td width=\"600\" height=\"90\" valign=\"top\" style=\"background: green\">\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" align=\"center\" cellspacing=\"0\" style=\"margin:0 auto;padding:0\"\n" +
                            "                            width=\"600\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"left\" width=\"150\" style=\"padding-left:56px;width:150px\">\n" +
                            "                                        <p style=\"font-size: 2rem; color: white\">Growth System</p>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"padding: 30px 0\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"center\" style=\"background:#fff;padding:0 56px;width:100%;\">\n" +
                            "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                            "                                            <tbody>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding: 0 0 15px 0\">Hola</td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding: 0\">Tal parece que has olvidado tu clave, para reiniciar tu clave click en el boton de abajo:</td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding:24px 0\">\n" +
                            "                                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"10\"\n" +
                            "                                                            align=\"center\">\n" +
                            "                                                            <tbody>\n" +
                            "                                                                <tr>\n" +
                            "                                                                    <td align=\"center\" bgcolor=\"black\"\n" +
                            "                                                                        style=\"color:#000000;font-size:14px;border-radius:4px; background: #F7931E;\"\n" +
                            "                                                                        valign=\"top\">\n" +
                            "                                                                        <font face=\"'Oracle Sans', sans-serif\">\n" +
                            "                                                                            <a style=\"display: inline-block; text-decoration: none; color: black; font-weight: bold;\" href=\"" + link + "\" target=\"_self\">Cambiar contraseña</a>\n" +
                            "                                                                        </font>\n" +
                            "                                                                    </td>\n" +
                            "                                                                </tr>\n" +
                            "                                                            </tbody>\n" +
                            "                                                        </table>\n" +
                            "\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"padding:0\">\n" +
                            "                                                        Gracias, <br>\n" +
                            "                                                        <strong style=\"color:#766f6e\">Equipo de Growth System</strong>\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                            </tbody>\n" +
                            "                                        </table>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "                <tr>\n" +
                            "                    <td>\n" +
                            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                            "                            <tbody>\n" +
                            "                                <tr>\n" +
                            "                                    <td width=\"600\" height=\"40\" style=\"background: green;\">\n" +
                            "                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n" +
                            "                                            <tbody>\n" +
                            "                                                <tr>\n" +
                            "                                                    <td style=\"font-family:'Oracle Sans',sans-serif;font-size:12px;color:#ffffff;padding:15px 48px 0 56px\"\n" +
                            "                                                        width=\"100%\">Copyright @ 2021, Oracle y/o sus afiliadas. Todos\n" +
                            "                                                        los derechos reservados.</td>\n" +
                            "                                                </tr>\n" +
                            "\n" +
                            "                                                <tr>\n" +
                            "                                                    <td>\n" +
                            "                                                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                            "                                                            <tbody>\n" +
                            "                                                                <tr>\n" +
                            "                                                                    <td\n" +
                            "                                                                        style=\"font-size:12px;color:#ccc;font-weight:bold;padding-top:0;padding-right:10px;padding-bottom:0;padding-left:56px\">\n" +
                            "                                                                    </td>\n" +
                            "                                                                </tr>\n" +
                            "                                                            </tbody>\n" +
                            "                                                        </table>\n" +
                            "                                                    </td>\n" +
                            "                                                </tr>\n" +
                            "                                            </tbody>\n" +
                            "                                        </table>\n" +
                            "                                    </td>\n" +
                            "                                </tr>\n" +
                            "                            </tbody>\n" +
                            "                        </table>\n" +
                            "                    </td>\n" +
                            "                </tr>\n" +
                            "            </tbody>\n" +
                            "        </table>\n" +
                            "    </div>";
            return contenido;
        }
    }
}
