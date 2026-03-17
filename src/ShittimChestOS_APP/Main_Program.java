package ShittimChestOS_APP;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.URI;
import java.nio.file.*;

public class Main_Program {

    static final String VERSION = "Version 26.2";
    static final String BUILD = "Build 138A02";

    public static void main(String[] args) {
        System.out.println("————————————————————————————————————————");
        System.out.println("程序已启动，本窗口用于启动和日志或者错误信息显示");

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("关于 什亭之匣OS11");
            frame.setSize(750, 422);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setContentPane(new AboutPanel());
            frame.setVisible(true);
        });
        System.out.println("GUI窗口启动成功，大小：750X422，标题：\"关于 什亭之匣OS11\" 请前往GUI窗口执行操作");
        System.out.println("如果不需要该控制台窗口则可以将其最小化\n");
        try {
            Thread.sleep(550); // 暂停 550 毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("执行错误");
        }
        System.out.println("日志：");
        try {
            Thread.sleep(50); // 暂停 50 毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("执行错误");
        }
    }

    static class AboutPanel extends JPanel {

        BufferedImage bg;
        BufferedImage logo;

        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);

        // 占位符常量：请根据实际需要修改
        private static final String SUPPORT_URL = "https://space.bilibili.com/3546614589295022";          // 支持作者链接
        private static final String HOMEPAGE_URL = "https://space.bilibili.com/3546614589295022";         // 作者主页链接
        private static final String[] HELP_PDF_NAMES = {     // 帮助按钮对应的PDF文件名（不含路径）
                "01.pdf", "02.pdf", "03.pdf", "04.pdf"      // 分别对应行2到行5
        };
        private static final String[] COMPONENT_PY_SCRIPTS = {  // 可选组件按钮对应的Python脚本路径
                "C:\\Windows\\system32\\HelpAboutOS\\D-01.py", "C:\\Windows\\system32\\HelpAboutOS\\D-02.py", "C:\\Windows\\system32\\HelpAboutOS\\D-03.py", "C:\\Windows\\system32\\HelpAboutOS\\D-04.py", "C:\\Windows\\system32\\HelpAboutOS\\D-05.py" // 行1到行5分别对应
        };
        // PDF文件资源路径（在类路径根目录）
        private static final String PDF_RESOURCE_PATH = "/About&Author.pdf";

        // ========== 主副开发者相关链接 ==========
        private static final String MAIN_DEV_SUPPORT_URL = "https://space.bilibili.com/3546614589295022";   // 主开发者支持链接
        private static final String MAIN_DEV_HOMEPAGE_URL = "https://space.bilibili.com/3546614589295022";  // 主开发者主页
        private static final String SUB_DEV_SUPPORT_URL = "https://space.bilibili.com/3493077807270407";    // 副开发者支持链接
        private static final String SUB_DEV_HOMEPAGE_URL = "https://space.bilibili.com/3493077807270407";   // 副开发者主页

        public AboutPanel() {
            setLayout(new BorderLayout());
            loadImages();
            add(createTopTabs(), BorderLayout.NORTH);
            add(createContent(), BorderLayout.CENTER);
        }

        void loadImages() {
            try {
                bg = ImageIO.read(getClass().getResourceAsStream("/BG.png"));
                System.out.println("成功加载 \"BG.png\" 文件");
            } catch (IOException e) {
                e.printStackTrace(); // 背景图缺失时使用默认灰色背景
                System.out.println("无法加载或找到 背景“BG.png” 文件");
            }
            try {
                logo = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
                System.out.println("成功加载 \"logo.png\" 文件");
            } catch (IOException e) {
                e.printStackTrace(); // logo缺失时仅不显示
                System.out.println("无法加载或找到 Logo“logo.png” 文件");
            }
        }

        JPanel createTopTabs() {
            JPanel panel = new JPanel();
            panel.setOpaque(false);

            String[] tabs = { "主要", "帮助", "可选组件/功能", "项目作者" };

            for (String t : tabs) {
                JButton btn = new JButton(t);
                btn.setFocusPainted(false);
                btn.setBackground(new Color(240, 240, 240));
                btn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
                btn.addActionListener(e -> cardLayout.show(contentPanel, t));
                panel.add(btn);
            }
            return panel;
        }

        JPanel createContent() {
            contentPanel.setOpaque(false);
            contentPanel.add(mainPage(), "主要");
            contentPanel.add(helpPage(), "帮助");
            contentPanel.add(componentPage(), "可选组件/功能");
            contentPanel.add(authorPage(), "项目作者");
            return contentPanel;
        }

        // ==================== 主要选项卡 ====================
        JPanel mainPage() {
            JPanel panel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int centerX = getWidth() / 2;
                    if (logo != null) {
                        g2.drawImage(logo, centerX - 64, 70, 128, 128, null);
                    }

                    g2.setFont(new Font("SansSerif", Font.BOLD, 28));
                    String name = "什亭之匣 OS 11";
                    int w = g2.getFontMetrics().stringWidth(name);
                    g2.setColor(Color.BLACK);
                    g2.drawString(name, centerX - w / 2, 230);

                    g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
                    String v = VERSION + "   " + BUILD;
                    int vw = g2.getFontMetrics().stringWidth(v);
                    g2.drawString(v, centerX - vw / 2, 255);
                }
            };
            panel.setOpaque(false);

            // 底部按钮面板
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
            bottomPanel.setOpaque(false);

            Color skyBlue = new Color(135, 206, 235);

            // 支持作者按钮显示对话框
            JButton supportBtn = createSkyBlueButton("支持作者", skyBlue);
            supportBtn.addActionListener(e -> showSupportDialog());
            bottomPanel.add(supportBtn);

            JButton homeBtn = createSkyBlueButton("作者主页", skyBlue);
            homeBtn.addActionListener(e -> openWebpage(HOMEPAGE_URL));
            bottomPanel.add(homeBtn);

            panel.add(bottomPanel, BorderLayout.SOUTH);
            return panel;
        }

        // ==================== 帮助选项卡 ====================
        JPanel helpPage() {
            JPanel panel = new JPanel(null); // 绝对布局
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(750, 350)); // 与内容区匹配

            // 标题
            JLabel title = new JLabel("帮助", SwingConstants.CENTER);
            title.setFont(new Font("SansSerif", Font.BOLD, 18));
            title.setBounds(0, 20, 750, 30);
            panel.add(title);

            // 文本行（使用JLabel便于定位）
            String[] lines = {
                    "欢迎使用 什亭之匣OS 11。",
                    "什亭之匣OS 11系统美化帮助",
                    "什亭之匣工具文件夹内容使用帮助（包括日服BA汉化）",
                    "查看该定制操作系统的完整开发人员名单和法律信息",
                    "系统故障紧急处理检查单"
            };

            int startY = 70;      // 起始Y坐标
            int lineHeight = 28;   // 行高
            int btnX = 620;        // 按钮X坐标（右对齐）
            int btnWidth = 90;      // 按钮宽度

            Color skyBlue = new Color(135, 206, 235);

            for (int i = 0; i < lines.length; i++) {
                JLabel lineLabel = new JLabel(lines[i]);
                lineLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                lineLabel.setBounds(60, startY + i * lineHeight, 500, lineHeight);
                panel.add(lineLabel);

                // 第2行至第5行添加按钮（索引1到4）
                if (i >= 1 && i <= 4) {
                    JButton btn = createSkyBlueButton("查看文档", skyBlue);
                    int btnY = startY + i * lineHeight + (lineHeight - 25) / 2; // 垂直居中
                    btn.setBounds(btnX, btnY, btnWidth, 25);
                    int pdfIndex = i - 1; // 对应HELP_PDF_NAMES索引0~3
                    btn.addActionListener(e -> openHelpPDF(HELP_PDF_NAMES[pdfIndex]));
                    panel.add(btn);
                }
            }

            return panel;
        }

        // ==================== 可选组件/功能选项卡 ====================
        JPanel componentPage() {
            JPanel panel = new JPanel(null);
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(750, 350));

            // 读取 systemLXPD.txt 内容
            String lxpdContent = readSystemLXPDFile();
            boolean showButtons = "SE".equals(lxpdContent); // 只有内容为 SE 才显示按钮

            // 标题
            JLabel title = new JLabel("可选组件 / 功能", SwingConstants.CENTER);
            title.setFont(new Font("SansSerif", Font.BOLD, 18));
            title.setBounds(0, 20, 750, 30);
            panel.add(title);

            String[] lines = {
                    "完整的什亭之匣工具文件夹下载",
                    "完整的小红车视频壁纸文件下载",
                    "完整的附带软件下载",
                    "BlueArchive下载",
                    "完整的媒体文件下载"
            };

            int startY = 70;
            int lineHeight = 28;
            int btnX = 620;
            int btnWidth = 80;
            String suffix = "    ——该系统已包含该内容，无需下载";
            Color skyBlue = new Color(135, 206, 235);

            for (int i = 0; i < lines.length; i++) {
                String text = lines[i];
                if (!showButtons) {
                    text += suffix;
                }
                JLabel lineLabel = new JLabel(text);
                lineLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                lineLabel.setBounds(60, startY + i * lineHeight, 600, lineHeight);
                panel.add(lineLabel);

                if (showButtons) {
                    JButton btn = createSkyBlueButton("下载", skyBlue);
                    int btnY = startY + i * lineHeight + (lineHeight - 25) / 2;
                    btn.setBounds(btnX, btnY, btnWidth, 25);
                    int scriptIndex = i;
                    btn.addActionListener(e -> confirmAndRunPython(COMPONENT_PY_SCRIPTS[scriptIndex]));
                    panel.add(btn);
                }
            }

            return panel;
        }

        // ==================== 关于作者选项卡（修改后） ====================
        JPanel authorPage() {
            JPanel panel = new JPanel(null);
            panel.setOpaque(false);
            panel.setPreferredSize(new Dimension(750, 350));

            // 标题
            JLabel title = new JLabel("项目作者列表", SwingConstants.CENTER);
            title.setFont(new Font("SansSerif", Font.BOLD, 18));
            title.setBounds(0, 20, 750, 30);
            panel.add(title);

            // 文本内容（多行）
            String text = "ShittimChestOS 11 Project\n\n" +
                    "主开发者：你会Play_games吗\n" +
                    "副开发者：星野的b站之旅\n\n" +
                    "该软件UI 设计：ShittimChestOS Team\n" +
                    "该软件GUI编程：你会Play_games吗\n" +
                    "该软件版本：V1.2.6\n\n" +
                    "ShittimChestOS Team © 2026";
            JTextArea textArea = new JTextArea(text);
            textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
            textArea.setEditable(false);
            textArea.setOpaque(false);
            textArea.setBounds(60, 60, 250, 150);
            panel.add(textArea);

            Color skyBlue = new Color(135, 206, 235);
            int btnWidth = 120;
            int btnHeight = 25;
            int startX = 305; // 按钮起始X
            int spacing = 10;

            // 主开发者按钮行 (y = 95)
            JButton mainSupportBtn = createSkyBlueButton("支持主开发者", skyBlue);
            mainSupportBtn.setBounds(startX, 95, btnWidth, btnHeight);
            // 修改：添加对话框
            mainSupportBtn.addActionListener(e -> showSupportDialog("支持主开发者", MAIN_DEV_SUPPORT_URL));
            panel.add(mainSupportBtn);

            JButton mainHomeBtn = createSkyBlueButton("主开发者主页", skyBlue);
            mainHomeBtn.setBounds(startX + btnWidth + spacing, 95, btnWidth, btnHeight);
            mainHomeBtn.addActionListener(e -> openWebpage(MAIN_DEV_HOMEPAGE_URL));
            panel.add(mainHomeBtn);

            // 副开发者按钮行 (y = 125)
            JButton subSupportBtn = createSkyBlueButton("支持副开发者", skyBlue);
            subSupportBtn.setBounds(startX, 125, btnWidth, btnHeight);
            // 修改：添加对话框
            subSupportBtn.addActionListener(e -> showSupportDialog("支持副开发者", SUB_DEV_SUPPORT_URL));
            panel.add(subSupportBtn);

            JButton subHomeBtn = createSkyBlueButton("副开发者主页", skyBlue);
            subHomeBtn.setBounds(startX + btnWidth + spacing, 125, btnWidth, btnHeight);
            subHomeBtn.addActionListener(e -> openWebpage(SUB_DEV_HOMEPAGE_URL));
            panel.add(subHomeBtn);

            // PDF按钮
            JButton pdfBtn = createSkyBlueButton("详细开发人员及法律信息", skyBlue);
            pdfBtn.setBounds(250, 250, 250, 30);
            pdfBtn.addActionListener(e -> openResourcePDF());
            panel.add(pdfBtn);

            return panel;
        }

        // ==================== 通用辅助方法 ====================

        private JButton createSkyBlueButton(String text, Color bgColor) {
            JButton btn = new JButton(text);
            btn.setBackground(bgColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            return btn;
        }

        private String readSystemLXPDFile() {
            Path path = Paths.get("C:\\Windows\\system32\\systemLXPD.txt");
            try {
                String content = Files.readString(path).trim();
                return content;
            } catch (IOException e) {
                System.out.println("无法打开或找到 \"systemLXPD.txt\" 文件");
                return "0"; // 默认不显示按钮
            }
        }

        private void openWebpage(String url) {
            if (url == null || url.isEmpty()) {
                System.out.println("网址未配置，请修改相应的URL常量");
                return;
            }
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                System.out.println("无法打开网址: " + url);
            }
        }

        // 原有支持作者对话框（保持不变）
        private void showSupportDialog() {
            int result = JOptionPane.showConfirmDialog(this,
                    "该项目还没有单独制作爱发电等赞助方式，如果想支持作者就到作者主页充电支持吧！",
                    "支持作者",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                openWebpage(SUPPORT_URL);
            }
        }

        // 新增通用对话框方法
        private void showSupportDialog(String title, String url) {
            int result = JOptionPane.showConfirmDialog(this,
                    "该项目还没有单独制作爱发电等赞助方式，如果想支持作者就到作者主页充电支持吧！",
                    title,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                openWebpage(url);
            }
        }

        // 打开帮助PDF（从系统路径）
        private void openHelpPDF(String pdfName) {
            String basePath = "C:\\Windows\\system32\\HelpAboutOS\\";
            File pdfFile = new File(basePath + pdfName);
            if (!pdfFile.exists()) {
                System.out.println("无法打开或找到 \"" + pdfFile.getAbsolutePath() + "\" 文件");
                return;
            }
            try {
                Desktop.getDesktop().open(pdfFile);
                System.out.println("成功打开 \"" + pdfFile.getAbsolutePath() + "\" 文件");
            } catch (IOException e) {
                System.out.println("无法打开PDF文件: " + pdfFile.getAbsolutePath());
            }
        }

        // 打开资源文件夹中的PDF（About&Author.pdf）
        private void openResourcePDF() {
            // 从类路径获取资源流
            try (InputStream in = getClass().getResourceAsStream(PDF_RESOURCE_PATH)) {
                if (in == null) {
                    System.out.println("无法打开或找到资源文件: " + PDF_RESOURCE_PATH);
                    return;
                }
                // 创建临时文件
                File tempFile = File.createTempFile("About&Author", ".pdf");
                tempFile.deleteOnExit(); // 程序退出时自动删除
                try (OutputStream out = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
                // 打开临时文件
                Desktop.getDesktop().open(tempFile);
                System.out.println("成功打开资源PDF文件: " + PDF_RESOURCE_PATH + " (临时文件: " + tempFile.getAbsolutePath() + ")");
            } catch (IOException e) {
                System.out.println("无法打开PDF资源文件: " + PDF_RESOURCE_PATH);
            }
        }

        private void confirmAndRunPython(String scriptPath) {
            if (scriptPath == null || scriptPath.isEmpty()) {
                System.out.println("Python脚本路径未配置，请修改 COMPONENT_PY_SCRIPTS");
                return;
            }

            int result = JOptionPane.showConfirmDialog(this,
                    "您是否确定下载该内容，可能需要额外的硬盘空间和Internet资源\n" +
                            "如果您使用流量计费的上网方式可能会造成额外的费用",
                    "确认下载",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                System.out.println("正在执行 \"" + scriptPath + "\" python脚本");
                new Thread(() -> {
                    try {
                        ProcessBuilder pb = new ProcessBuilder("python", scriptPath);
                        pb.inheritIO();
                        Process p = pb.start();
                        int exitCode = p.waitFor();
                        System.out.println("Python下载文件脚本执行完成，退出码: " + exitCode);
                    } catch (Exception e) {
                        System.out.println("无法执行Python脚本: " + scriptPath);
                    }
                }).start();
            }
        }

        // ==================== 背景绘制 ====================
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            if (bg != null) {
                g2.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
                g2.setColor(new Color(255, 255, 255, 90));
                g2.fillRect(0, 0, getWidth(), getHeight());
            } else {
                g2.setColor(new Color(220, 220, 220));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
            g2.dispose();
        }
    }
}