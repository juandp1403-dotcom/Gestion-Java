package modelo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class ThemeUtil {

    // ── Colores SENA ──────────────────────────────────────────
    public static final Color PRIMARY       = new Color(40, 167, 69);
    public static final Color PRIMARY_DARK  = new Color(33, 136, 56);
    public static final Color PRIMARY_LIGHT = new Color(72, 199, 107);
    public static final Color BG            = new Color(245, 245, 245);
    public static final Color BG_DARK       = new Color(230, 230, 230);
    public static final Color TEXT          = new Color(51, 51, 51);
    public static final Color TEXT_LIGHT    = new Color(120, 120, 120);
    public static final Color BORDER        = new Color(206, 212, 218);
    public static final Color TABLE_GRID    = new Color(220, 220, 220);
    public static final Color TABLE_ALT_ROW = new Color(248, 252, 248);
    public static final Color WHITE         = Color.WHITE;
    public static final Color BLACK         = Color.BLACK;

    // ── Fuentes ───────────────────────────────────────────────
    public static final Font FONT_TITLE        = new Font("Arial", Font.BOLD, 16);
    public static final Font FONT_TITLE_BIG    = new Font("Arial", Font.BOLD, 18);
    public static final Font FONT_SUBTITLE     = new Font("Arial", Font.PLAIN, 11);
    public static final Font FONT_SECTION      = new Font("Arial", Font.BOLD, 14);
    public static final Font FONT_BODY         = new Font("Arial", Font.PLAIN, 12);
    public static final Font FONT_BODY_BOLD    = new Font("Arial", Font.BOLD, 12);
    public static final Font FONT_FIELD        = new Font("Arial", Font.PLAIN, 13);
    public static final Font FONT_BTN          = new Font("Arial", Font.BOLD, 13);
    public static final Font FONT_BTN_SM       = new Font("Arial", Font.BOLD, 11);
    public static final Font FONT_TABLE_HEADER = new Font("Arial", Font.BOLD, 12);
    public static final Font FONT_TABLE_CELL   = new Font("Arial", Font.PLAIN, 12);

    // ── Header verde ──────────────────────────────────────────
    public static void styleHeader(JPanel header) {
        header.setBackground(PRIMARY);
        for (Component c : header.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setForeground(WHITE);
            }
            if (c instanceof JButton) {
                styleHeaderButton((JButton) c);
            }
        }
    }

    public static void styleHeaderButton(JButton btn) {
        btn.setBackground(PRIMARY_DARK);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFont(FONT_BTN_SM);
    }

    // ── Botón primario (relleno verde) ────────────────────────
    public static void stylePrimaryButton(JButton btn) {
        stylePrimaryButton(btn, FONT_BTN);
    }

    public static void stylePrimaryButton(JButton btn, Font font) {
        btn.setBackground(PRIMARY);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFont(font);
    }

    // ── Botón secundario (borde verde, fondo blanco) ──────────
    public static void styleSecondaryButton(JButton btn) {
        styleSecondaryButton(btn, FONT_BTN_SM);
    }

    public static void styleSecondaryButton(JButton btn, Font font) {
        btn.setBackground(WHITE);
        btn.setForeground(PRIMARY);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(PRIMARY, 2));
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFont(font);
    }

    // ── TextField ─────────────────────────────────────────────
    public static void styleTextField(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER, 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        txt.setFont(FONT_FIELD);
    }

    public static void stylePasswordField(JPasswordField pf) {
        pf.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER, 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        pf.setFont(FONT_FIELD);
    }

    // ── ComboBox ──────────────────────────────────────────────
    public static void styleComboBox(JComboBox<?> cbx) {
        cbx.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER, 1, true),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        cbx.setFont(FONT_FIELD);
        cbx.setBackground(WHITE);
        cbx.setForeground(TEXT);
        cbx.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // ── Label ─────────────────────────────────────────────────
    public static void styleLabel(JLabel lbl, Color fg, Font font) {
        lbl.setForeground(fg);
        lbl.setFont(font);
    }

    public static void styleLabel(JLabel lbl) {
        styleLabel(lbl, TEXT, FONT_BODY);
    }

    public static void styleSectionTitle(JLabel lbl) {
        styleLabel(lbl, PRIMARY, FONT_SECTION);
    }

    // ── Tarjeta (card panel) ──────────────────────────────────
    public static void styleCard(JPanel card) {
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PRIMARY, 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    public static void styleCard(JPanel card, int topPadding) {
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(PRIMARY, 2, true),
            BorderFactory.createEmptyBorder(topPadding, 15, 15, 15)
        ));
    }

    // ── Tabla ─────────────────────────────────────────────────
    public static void styleTable(JTable table) {
        table.setBackground(WHITE);
        table.setSelectionBackground(PRIMARY);
        table.setSelectionForeground(WHITE);
        table.setGridColor(TABLE_GRID);
        table.setRowHeight(28);
        table.setFont(FONT_TABLE_CELL);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));

        // Header
        JTableHeader header = table.getTableHeader();
        header.setBackground(PRIMARY);
        header.setForeground(WHITE);
        header.setFont(FONT_TABLE_HEADER);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // Renderer de celdas
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBackground(WHITE);
        cellRenderer.setForeground(TEXT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Header renderer
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(PRIMARY);
        headerRenderer.setForeground(WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(FONT_TABLE_HEADER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    // ── Content pane (fondo gris) ─────────────────────────────
    public static void styleContentPane(JInternalFrame frame) {
        frame.getContentPane().setBackground(BG);
    }

    public static void styleContentPane(javax.swing.JDialog dialog) {
        dialog.getContentPane().setBackground(BG);
    }

    // ── ScrollPane ────────────────────────────────────────────
    public static void styleScrollPane(JScrollPane sp) {
        sp.setBorder(new LineBorder(BORDER, 1));
        sp.getViewport().setBackground(WHITE);
    }

    // ── Marcar todos los campos como solo lectura ──────────────
    public static void readOnly(JTextField txt) {
        txt.setEditable(false);
        txt.setBackground(BG_DARK);
        txt.setForeground(TEXT);
    }

    public static void readOnly(JTextArea area) {
        area.setEditable(false);
        area.setBackground(BG_DARK);
        area.setForeground(TEXT);
    }
}
