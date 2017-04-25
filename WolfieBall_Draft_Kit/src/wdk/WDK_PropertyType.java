package wdk;

/**
 * These are properties that are to be loaded from properties.xml. They
 * will provide custom labels and other UI details for our Course Site Builder
 * application. The reason for doing this is to swap out UI text and icons
 * easily without having to touch our code. It also allows for language
 * independence.
 * 
 * @author Richard McKenna
 */
public enum WDK_PropertyType {
        // LOADED FROM properties.xml
        PROP_APP_TITLE,
        
        // APPLICATION ICONS
        EDIT_ICON,
        NEW_COURSE_ICON,
        LOAD_COURSE_ICON,
        SAVE_COURSE_ICON,
        VIEW_SCHEDULE_ICON,
        EXPORT_PAGE_ICON,
        DELETE_ICON,
        EXIT_ICON,
        ADD_ICON,
        MINUS_ICON,
        MOVE_UP_ICON,
        MOVE_DOWN_ICON,
        PLAYER_ICON,
        HOME_ICON,
        DRAFT_ICON,
        MLB_TEAM_ICON,
        FANTASY_STANDING_ICON,
        SEARCH_LABEL,
        DRAFT_LABEL,
        SELECT_TEAM_LABEL,
        START_LINE_LABEL,
        TAXI_QUAD_LABEL,
        MOVE_PLAYER_ICON,
        PLAY_ICON,
        STAR_ICON,
        PAUSE_ICON,
        
        // APPLICATION TOOLTIPS FOR BUTTONS
        NEW_COURSE_TOOLTIP,
        LOAD_COURSE_TOOLTIP,
        SAVE_COURSE_TOOLTIP,
        VIEW_SCHEDULE_TOOLTIP,
        EXPORT_PAGE_TOOLTIP,
        DELETE_TOOLTIP,
        EDIT_TOOLTIP,
        EXIT_TOOLTIP,
        ADD_ITEM_TOOLTIP,
        REMOVE_ITEM_TOOLTIP,
        ADD_LECTURE_TOOLTIP,
        REMOVE_LECTURE_TOOLTIP,
        MOVE_UP_LECTURE_TOOLTIP,
        MOVE_DOWN_LECTURE_TOOLTIP,
        ADD_HW_TOOLTIP,
        REMOVE_HW_TOOLTIP,
        PLAYER_TOOLTIP,
        HOME_TOOLTIP,
        DRAFT_TOOLTIP,
        MLB_TEAM_TOOLTIP,
        FANTASY_STANDING_TOOLTIP,

        // FOR COURSE EDIT WORKSPACE
        PLAYER_HEADING_LABEL,
        HOME_HEADING_LABEL,
        DRAFT_HEADING_LABEL,
        MLB_TEAM_HEADING_LABEL,
        FANTASY_STANDING_HEADING_LABEL,
        
        
        
        COURSE_HEADING_LABEL,
        COURSE_INFO_LABEL,
        COURSE_SUBJECT_LABEL,
        COURSE_NUMBER_LABEL,
        COURSE_SEMESTER_LABEL,
        COURSE_YEAR_LABEL,
        COURSE_TITLE_LABEL,
        INSTRUCTOR_NAME_LABEL,
        INSTRUCTOR_URL_LABEL,
        PAGES_SELECTION_HEADING_LABEL,
        SCHEDULE_ITEMS_HEADING_LABEL,
        LECTURES_HEADING_LABEL,
        HWS_HEADING_LABEL,
        MLBTEAM_LABEL,
        
        //RADIO BUTTON LABEL
        ALL_LABEL,
        C_LABEL,
        B1_LABEL,
        CI_LABEL,
        B3_LABEL,
        B2_LABEL,
        MI_LABEL,
        SS_LABEL,
        OF_LABEL,
        U_LABEL,
        P_LABEL,

        // PAGE CHECKBOX LABELS
        INDEX_CHECKBOX_LABEL,
        SYLLABUS_CHECKBOX_LABEL,
        SCHEDULE_CHECKBOX_LABEL,
        HWS_CHECKBOX_LABEL,
        PROJECTS_CHECKBOX_LABEL,
                
        // FOR SCHEDULE EDITING
        SCHEDULE_HEADING_LABEL,
        DATE_BOUNDARIES_LABEL,
        STARTING_MONDAY_LABEL,
        ENDING_FRIDAY_LABEL,
        LECTURE_DAY_SELECT_LABEL,
        
        // ERROR DIALOG MESSAGES
        START_DATE_AFTER_END_DATE_ERROR_MESSAGE,
        START_DATE_NOT_A_MONDAY_ERROR_MESSAGE,
        END_DATE_NOT_A_FRIDAY_ERROR_MESSAGE,
        ILLEGAL_DATE_MESSAGE,
        
        // AND VERIFICATION MESSAGES
        NEW_DRAFT_CREATED_MESSAGE,
        COURSE_LOADED_MESSAGE,
        COURSE_SAVED_MESSAGE,
        SITE_EXPORTED_MESSAGE,
        SAVE_UNSAVED_WORK_MESSAGE,
        REMOVE_ITEM_MESSAGE
}
