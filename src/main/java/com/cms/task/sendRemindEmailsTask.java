package com.cms.task;

import com.cms.pojo.Activity;
import com.cms.pojo.Subscribe;
import com.cms.pojo.User;
import com.cms.service.ActivityService;
import com.cms.service.SubscribeService;
import com.cms.service.UserService;
import lombok.experimental.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Component
public class sendRemindEmailsTask {
    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    /**
     * 注解中填入cron表达式
     * 下述是每一分钟执行
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendRemindEmails() throws Exception{
        log.info("执行定时任务！发送提醒邮件！");
        List<Subscribe> subscribeList = subscribeService.selectSubscribeActivityListByDate();
        if(subscribeList != null){
            for (Subscribe subscribe : subscribeList){
                Subscribe temp = new Subscribe();
                temp.setId(subscribe.getId());
                temp.setStatus(0);
                int result = subscribeService.updateByPrimaryKeySelective(temp);
                if(result <= 0){
                    log.info("更新订阅信息失败！   "+ subscribe.getId() +"号订阅记录未发送提醒邮件!");
                }else {
                    Activity activity = activityService.selectActivityById(subscribe.getActivityId());
                    User user = userService.selectUserById(subscribe.getUserId());
                    String html = "<!doctype html>\n" +
                            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                            "<head>\n" +
                            "    <!-- NAME: NEW COLLECTION -->\n" +
                            "    <!--[if gte mso 15]>\n" +
                            "    <xml>\n" +
                            "        <o:OfficeDocumentSettings>\n" +
                            "            <o:AllowPNG/>\n" +
                            "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                            "        </o:OfficeDocumentSettings>\n" +
                            "    </xml>\n" +
                            "    <![endif]-->\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                            "    <title>*|MC:SUBJECT|*</title>\n" +
                            "\n" +
                            "    <style type=\"text/css\">\n" +
                            "        p{\n" +
                            "            margin:10px 0;\n" +
                            "            padding:0;\n" +
                            "        }\n" +
                            "        table{\n" +
                            "            border-collapse:collapse;\n" +
                            "        }\n" +
                            "        h1,h2,h3,h4,h5,h6{\n" +
                            "            display:block;\n" +
                            "            margin:0;\n" +
                            "            padding:0;\n" +
                            "        }\n" +
                            "        img,a img{\n" +
                            "            border:0;\n" +
                            "            height:auto;\n" +
                            "            outline:none;\n" +
                            "            text-decoration:none;\n" +
                            "        }\n" +
                            "        body,#bodyTable,#bodyCell{\n" +
                            "            height:100%;\n" +
                            "            margin:0;\n" +
                            "            padding:0;\n" +
                            "            width:100%;\n" +
                            "        }\n" +
                            "        .mcnPreviewText{\n" +
                            "            display:none !important;\n" +
                            "        }\n" +
                            "        #outlook a{\n" +
                            "            padding:0;\n" +
                            "        }\n" +
                            "        img{\n" +
                            "            -ms-interpolation-mode:bicubic;\n" +
                            "        }\n" +
                            "        table{\n" +
                            "            mso-table-lspace:0pt;\n" +
                            "            mso-table-rspace:0pt;\n" +
                            "        }\n" +
                            "        .ReadMsgBody{\n" +
                            "            width:100%;\n" +
                            "        }\n" +
                            "        .ExternalClass{\n" +
                            "            width:100%;\n" +
                            "        }\n" +
                            "        p,a,li,td,blockquote{\n" +
                            "            mso-line-height-rule:exactly;\n" +
                            "        }\n" +
                            "        a[href^=tel],a[href^=sms]{\n" +
                            "            color:inherit;\n" +
                            "            cursor:default;\n" +
                            "            text-decoration:none;\n" +
                            "        }\n" +
                            "        p,a,li,td,body,table,blockquote{\n" +
                            "            -ms-text-size-adjust:100%;\n" +
                            "            -webkit-text-size-adjust:100%;\n" +
                            "        }\n" +
                            "        .ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{\n" +
                            "            line-height:100%;\n" +
                            "        }\n" +
                            "        a[x-apple-data-detectors]{\n" +
                            "            color:inherit !important;\n" +
                            "            text-decoration:none !important;\n" +
                            "            font-size:inherit !important;\n" +
                            "            font-family:inherit !important;\n" +
                            "            font-weight:inherit !important;\n" +
                            "            line-height:inherit !important;\n" +
                            "        }\n" +
                            "        .templateContainer{\n" +
                            "            max-width:600px !important;\n" +
                            "        }\n" +
                            "        a.mcnButton{\n" +
                            "            display:block;\n" +
                            "        }\n" +
                            "        .mcnImage{\n" +
                            "            vertical-align:bottom;\n" +
                            "        }\n" +
                            "        .mcnTextContent{\n" +
                            "            word-break:break-word;\n" +
                            "        }\n" +
                            "        .mcnTextContent img{\n" +
                            "            height:auto !important;\n" +
                            "        }\n" +
                            "        .mcnDividerBlock{\n" +
                            "            table-layout:fixed !important;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Background Style\n" +
                            "        @tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.\n" +
                            "        */\n" +
                            "        body,#bodyTable{\n" +
                            "            /*@editable*/background-color:#FFFFFF;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Background Style\n" +
                            "        @tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.\n" +
                            "        */\n" +
                            "        #bodyCell{\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Heading 1\n" +
                            "        @tip Set the styling for all first-level headings in your emails. These should be the largest of your headings.\n" +
                            "        @style heading 1\n" +
                            "        */\n" +
                            "        h1{\n" +
                            "            /*@editable*/color:#FFFFFF;\n" +
                            "            /*@editable*/font-family:Georgia;\n" +
                            "            /*@editable*/font-size:35px;\n" +
                            "            /*@editable*/font-style:normal;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/line-height:200%;\n" +
                            "            /*@editable*/letter-spacing:normal;\n" +
                            "            /*@editable*/text-align:center;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Heading 2\n" +
                            "        @tip Set the styling for all second-level headings in your emails.\n" +
                            "        @style heading 2\n" +
                            "        */\n" +
                            "        h2{\n" +
                            "            /*@editable*/color:#FFFFFF;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:30px;\n" +
                            "            /*@editable*/font-style:normal;\n" +
                            "            /*@editable*/font-weight:bold;\n" +
                            "            /*@editable*/line-height:125%;\n" +
                            "            /*@editable*/letter-spacing:normal;\n" +
                            "            /*@editable*/text-align:center;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Heading 3\n" +
                            "        @tip Set the styling for all third-level headings in your emails.\n" +
                            "        @style heading 3\n" +
                            "        */\n" +
                            "        h3{\n" +
                            "            /*@editable*/color:#393939;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:20px;\n" +
                            "            /*@editable*/font-style:normal;\n" +
                            "            /*@editable*/font-weight:bold;\n" +
                            "            /*@editable*/line-height:125%;\n" +
                            "            /*@editable*/letter-spacing:normal;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Page\n" +
                            "        @section Heading 4\n" +
                            "        @tip Set the styling for all fourth-level headings in your emails. These should be the smallest of your headings.\n" +
                            "        @style heading 4\n" +
                            "        */\n" +
                            "        h4{\n" +
                            "            /*@editable*/color:#999999;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:18px;\n" +
                            "            /*@editable*/font-style:normal;\n" +
                            "            /*@editable*/font-weight:bold;\n" +
                            "            /*@editable*/line-height:125%;\n" +
                            "            /*@editable*/letter-spacing:normal;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Preheader\n" +
                            "        @section Preheader Style\n" +
                            "        @tip Set the background color and borders for your email's preheader area.\n" +
                            "        */\n" +
                            "        #templatePreheader{\n" +
                            "            /*@editable*/background-color:#FFFFFF;\n" +
                            "            /*@editable*/background-image:none;\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:0;\n" +
                            "            /*@editable*/padding-top:9px;\n" +
                            "            /*@editable*/padding-bottom:9px;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Preheader\n" +
                            "        @section Preheader Text\n" +
                            "        @tip Set the styling for your email's preheader text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n" +
                            "            /*@editable*/color:#656565;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:12px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Preheader\n" +
                            "        @section Preheader Link\n" +
                            "        @tip Set the styling for your email's preheader links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templatePreheader .mcnTextContent a,#templatePreheader .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#656565;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Header\n" +
                            "        @section Header Style\n" +
                            "        @tip Set the background color and borders for your email's header area.\n" +
                            "        */\n" +
                            "        #templateHeader{\n" +
                            "            /*@editable*/background-color:#E6A56A;\n" +
                            "            /*@editable*/background-image:url(https://cdn-images.mailchimp.com/template_images/gallery/furniture6.jpeg);\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:0;\n" +
                            "            /*@editable*/padding-top:80px;\n" +
                            "            /*@editable*/padding-bottom:80px;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Header\n" +
                            "        @section Header Text\n" +
                            "        @tip Set the styling for your email's header text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n" +
                            "            /*@editable*/color:#FFFFFF;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:20px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:center;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Header\n" +
                            "        @section Header Link\n" +
                            "        @tip Set the styling for your email's header links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templateHeader .mcnTextContent a,#templateHeader .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#FFFFFF;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Body\n" +
                            "        @section Body Style\n" +
                            "        @tip Set the background color and borders for your email's body area.\n" +
                            "        */\n" +
                            "        #templateBody{\n" +
                            "            /*@editable*/background-color:#FFFFFF;\n" +
                            "            /*@editable*/background-image:none;\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:0;\n" +
                            "            /*@editable*/padding-top:80px;\n" +
                            "            /*@editable*/padding-bottom:0;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Body\n" +
                            "        @section Body Text\n" +
                            "        @tip Set the styling for your email's body text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n" +
                            "            /*@editable*/color:#202020;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:18px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:center;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Body\n" +
                            "        @section Body Link\n" +
                            "        @tip Set the styling for your email's body links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templateBody .mcnTextContent a,#templateBody .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#ED5A2E;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Upper Columns\n" +
                            "        @section Upper Column Style\n" +
                            "        @tip Set the background color and borders for your email's upper columns.\n" +
                            "        */\n" +
                            "        #templateUpperColumns{\n" +
                            "            /*@editable*/background-color:#FFFFFF;\n" +
                            "            /*@editable*/background-image:none;\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:0;\n" +
                            "            /*@editable*/padding-top:0;\n" +
                            "            /*@editable*/padding-bottom:80px;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Upper Columns\n" +
                            "        @section Upper Column Text\n" +
                            "        @tip Set the styling for your email's upper column text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templateUpperColumns .columnContainer .mcnTextContent,#templateUpperColumns .columnContainer .mcnTextContent p{\n" +
                            "            /*@editable*/color:#202020;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:16px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Upper Columns\n" +
                            "        @section Upper Column Link\n" +
                            "        @tip Set the styling for your email's upper column links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templateUpperColumns .columnContainer .mcnTextContent a,#templateUpperColumns .columnContainer .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#ED5A2E;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Lower Columns\n" +
                            "        @section Lower Column Style\n" +
                            "        @tip Set the background color and borders for your email's lower columns.\n" +
                            "        */\n" +
                            "        #templateLowerColumns{\n" +
                            "            /*@editable*/background-color:#EEEEEE;\n" +
                            "            /*@editable*/background-image:none;\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:2px solid #EAEAEA;\n" +
                            "            /*@editable*/padding-top:80px;\n" +
                            "            /*@editable*/padding-bottom:80px;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Lower Columns\n" +
                            "        @section Lower Column Text\n" +
                            "        @tip Set the styling for your email's lower column text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templateLowerColumns .columnContainer .mcnTextContent,#templateLowerColumns .columnContainer .mcnTextContent p{\n" +
                            "            /*@editable*/color:#202020;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:16px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Lower Columns\n" +
                            "        @section Lower Column Link\n" +
                            "        @tip Set the styling for your email's lower column links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templateLowerColumns .columnContainer .mcnTextContent a,#templateLowerColumns .columnContainer .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#666666;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Footer\n" +
                            "        @section Footer Style\n" +
                            "        @tip Set the background color and borders for your email's footer area.\n" +
                            "        */\n" +
                            "        #templateFooter{\n" +
                            "            /*@editable*/background-color:#FFFFFF;\n" +
                            "            /*@editable*/background-image:none;\n" +
                            "            /*@editable*/background-repeat:no-repeat;\n" +
                            "            /*@editable*/background-position:center;\n" +
                            "            /*@editable*/background-size:cover;\n" +
                            "            /*@editable*/border-top:0;\n" +
                            "            /*@editable*/border-bottom:0;\n" +
                            "            /*@editable*/padding-top:80px;\n" +
                            "            /*@editable*/padding-bottom:80px;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Footer\n" +
                            "        @section Footer Text\n" +
                            "        @tip Set the styling for your email's footer text. Choose a size and color that is easy to read.\n" +
                            "        */\n" +
                            "        #templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n" +
                            "            /*@editable*/color:#656565;\n" +
                            "            /*@editable*/font-family:Helvetica;\n" +
                            "            /*@editable*/font-size:12px;\n" +
                            "            /*@editable*/line-height:150%;\n" +
                            "            /*@editable*/text-align:left;\n" +
                            "        }\n" +
                            "        /*\n" +
                            "        @tab Footer\n" +
                            "        @section Footer Link\n" +
                            "        @tip Set the styling for your email's footer links. Choose a color that helps them stand out from your text.\n" +
                            "        */\n" +
                            "        #templateFooter .mcnTextContent a,#templateFooter .mcnTextContent p a{\n" +
                            "            /*@editable*/color:#656565;\n" +
                            "            /*@editable*/font-weight:normal;\n" +
                            "            /*@editable*/text-decoration:underline;\n" +
                            "        }\n" +
                            "        @media only screen and (min-width:768px){\n" +
                            "            .templateContainer{\n" +
                            "                width:600px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            body,table,td,p,a,li,blockquote{\n" +
                            "                -webkit-text-size-adjust:none !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            body{\n" +
                            "                width:100% !important;\n" +
                            "                min-width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            #bodyCell{\n" +
                            "                padding-top:10px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .columnWrapper{\n" +
                            "                max-width:100% !important;\n" +
                            "                width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImage{\n" +
                            "                width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnCartContainer,.mcnCaptionTopContent,.mcnRecContentContainer,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer{\n" +
                            "                max-width:100% !important;\n" +
                            "                width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnBoxedTextContentContainer{\n" +
                            "                min-width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageGroupContent{\n" +
                            "                padding:9px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{\n" +
                            "                padding-top:9px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageCardTopImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{\n" +
                            "                padding-top:18px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageCardBottomImageContent{\n" +
                            "                padding-bottom:9px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageGroupBlockInner{\n" +
                            "                padding-top:0 !important;\n" +
                            "                padding-bottom:0 !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageGroupBlockOuter{\n" +
                            "                padding-top:9px !important;\n" +
                            "                padding-bottom:9px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnTextContent,.mcnBoxedTextContentColumn{\n" +
                            "                padding-right:18px !important;\n" +
                            "                padding-left:18px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{\n" +
                            "                padding-right:18px !important;\n" +
                            "                padding-bottom:0 !important;\n" +
                            "                padding-left:18px !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            .mcpreview-image-uploader{\n" +
                            "                display:none !important;\n" +
                            "                width:100% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Heading 1\n" +
                            "            @tip Make the first-level headings larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            h1{\n" +
                            "                /*@editable*/font-size:22px !important;\n" +
                            "                /*@editable*/line-height:125% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Heading 2\n" +
                            "            @tip Make the second-level headings larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            h2{\n" +
                            "                /*@editable*/font-size:20px !important;\n" +
                            "                /*@editable*/line-height:125% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Heading 3\n" +
                            "            @tip Make the third-level headings larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            h3{\n" +
                            "                /*@editable*/font-size:18px !important;\n" +
                            "                /*@editable*/line-height:125% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Heading 4\n" +
                            "            @tip Make the fourth-level headings larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            h4{\n" +
                            "                /*@editable*/font-size:16px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Boxed Text\n" +
                            "            @tip Make the boxed text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                            "            */\n" +
                            "            .mcnBoxedTextContentContainer .mcnTextContent,.mcnBoxedTextContentContainer .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:14px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Preheader Visibility\n" +
                            "            @tip Set the visibility of the email's preheader on small screens. You can hide it to save space.\n" +
                            "            */\n" +
                            "            #templatePreheader{\n" +
                            "                /*@editable*/display:block !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Preheader Text\n" +
                            "            @tip Make the preheader text larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            #templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:14px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Header Text\n" +
                            "            @tip Make the header text larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            #templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:16px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Body Text\n" +
                            "            @tip Make the body text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                            "            */\n" +
                            "            #templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:16px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Upper Column Text\n" +
                            "            @tip Make the upper column text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                            "            */\n" +
                            "            #templateUpperColumns .columnContainer .mcnTextContent,#templateUpperColumns .columnContainer .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:16px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Lower Column Text\n" +
                            "            @tip Make the lower column text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                            "            */\n" +
                            "            #templateLowerColumns .columnContainer .mcnTextContent,#templateLowerColumns .columnContainer .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:16px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }\t@media only screen and (max-width: 480px){\n" +
                            "            /*\n" +
                            "            @tab Mobile Styles\n" +
                            "            @section Footer Text\n" +
                            "            @tip Make the footer content text larger in size for better readability on small screens.\n" +
                            "            */\n" +
                            "            #templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n" +
                            "                /*@editable*/font-size:14px !important;\n" +
                            "                /*@editable*/line-height:150% !important;\n" +
                            "            }\n" +
                            "\n" +
                            "        }</style></head>\n" +
                            "<body>\n" +
                            "<center>\n" +
                            "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\">\n" +
                            "        <tr>\n" +
                            "            <td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
                            "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                            "                    <tr>\n" +
                            "                        <td align=\"center\" valign=\"top\" id=\"templatePreheader\">\n" +
                            "                            <!--[if gte mso 9]>\n" +
                            "                            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" style=\"width:600px;\">\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"center\" valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                            "                            <![endif]-->\n" +
                            "                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\">\n" +
                            "                                <tr>\n" +
                            "                                    <td valign=\"top\" class=\"preheaderContainer\"></td>\n" +
                            "                                </tr>\n" +
                            "                            </table>\n" +
                            "                            <!--[if gte mso 9]>\n" +
                            "                            </td>\n" +
                            "                            </tr>\n" +
                            "                            </table>\n" +
                            "                            <![endif]-->\n" +
                            "                        </td>\n" +
                            "                    </tr>\n" +
                            "                    <tr>\n" +
                            "                        <td align=\"center\" valign=\"top\" id=\"templateHeader\">\n" +
                            "                            <!--[if gte mso 9]>\n" +
                            "                            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" style=\"width:600px;\">\n" +
                            "                                <tr>\n" +
                            "                                    <td align=\"center\" valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                            "                            <![endif]-->\n" +
                            "                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\">\n" +
                            "                                <tr>\n" +
                            "                                    <td valign=\"top\" class=\"headerContainer\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                            "                                        <tbody class=\"mcnTextBlockOuter\">\n" +
                            "                                        <tr>\n" +
                            "                                            <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                            "                                                <!--[if mso]>\n" +
                            "                                                <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                            "                                                    <tr>\n" +
                            "                                                <![endif]-->\n" +
                            "\n" +
                            "                                                <!--[if mso]>\n" +
                            "                                                <td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                            "                                                <![endif]-->\n" +
                            "                                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                            "                                                    <tbody><tr>\n" +
                            "\n" +
                            "                                                        <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n" +
                            "\n" +
                            "<h1>"+activity.getTitle()+"</h1>\n" +
                            "\n" +
                            "<a><img src="+activity.getImages()+"></a>\n" +
                            "\n" +
                            "<p>"+activity.getIntroduction()+"</p>\n" +
                            "\n" +
                            "</td>\n" +
                            "                                                    </tr>\n" +
                            "                                                    </tbody></table>\n" +
                            "                                                <!--[if mso]>\n" +
                            "                                                </td>\n" +
                            "                                                <![endif]-->\n" +
                            "\n" +
                            "                                                <!--[if mso]>\n" +
                            "                                                </tr>\n" +
                            "                                                </table>\n" +
                            "                                                <![endif]-->\n" +
                            "                                            </td>\n" +
                            "                                        </tr>\n" +
                            "                                        </tbody>\n" +
                            "                                    </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                            "                                        <tbody class=\"mcnDividerBlockOuter\">\n" +
                            "                                        <tr>\n" +
                            "                                            <td class=\"mcnDividerBlockInner\" style=\"min-width: 100%; padding: 20px 18px 0px;\">\n" +
                            "                                                <table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n" +
                            "                                                    <tbody><tr>\n" +
                            "                                                        <td>\n" +
                            "                                                            <span></span>\n" +
                            "                                                        </td>\n" +
                            "                                                    </tr>\n" +
                            "                                                    </tbody></table>\n" +
                            "                                                <!--\n" +
                            "                                                                <td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                            "                                                                <hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                            "                                                -->\n" +
                            "                                            </td>\n" +
                            "                                        </tr>\n" +
                            "                                        </tbody>\n" +
                            "                                    </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnButtonBlock\" style=\"min-width:100%;\">\n" +
                            "                                        <tbody class=\"mcnButtonBlockOuter\">\n" +
                            "                                        <tr>\n" +
                            "                                            <td style=\"padding-top:0; padding-right:18px; padding-bottom:18px; padding-left:18px;\" valign=\"top\" align=\"center\" class=\"mcnButtonBlockInner\">\n" +
                            "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"mcnButtonContentContainer\" style=\"border-collapse: separate !important;border-radius: 3px;background-color: #ED5A2E;\">\n" +
                            "                                                    <tbody>\n" +
                            "                                                    <tr>\n" +
                            "                                                        <td align=\"center\" valign=\"middle\" class=\"mcnButtonContent\" style=\"font-family: Arial; font-size: 16px; padding: 20px;\">\n" +
                            "                                                            <a class=\"mcnButton \" title=\"target=\"_blank\"\" href=\"http://www.baidu.com\" target=\"_blank\" style=\"font-weight: bold;letter-spacing: normal;line-height: 100%;text-align: center;text-decoration: none;color: #FFFFFF;\">了解详情</a>\n" +
                            "                                                        </td>\n" +
                            "                                                    </tr>\n" +
                            "                                                    </tbody>\n" +
                            "                                                </table>\n" +
                            "                                            </td>\n" +
                            "                                        </tr>\n" +
                            "                                        </tbody>\n" +
                            "                                    </table></td>\n" +
                            "                                </tr>\n" +
                            "                            </table>\n" +
                            "                            <!--[if gte mso 9]>\n" +
                            "                            </td>\n" +
                            "                            </tr>\n" +
                            "                            </table>\n" +
                            "                            <![endif]-->\n" +
                            "                        </td>\n" +
                            "                    </tr>\n" +
                            "                </table>\n" +
                            "            </td>\n" +
                            "        </tr>\n" +
                            "    </table>\n" +
                            "</center>\n" +
                            "</body>\n" +
                            "</html>";


                    userService.sendEmailHtmlContent(user.getEmail() , html);
                }
            }
        }
    }

}
